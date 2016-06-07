package com.baijia123.interrupt;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class WebCrawler {
	private class CrawlTask implements Runnable {
		private final URL url;

		public CrawlTask(URL url) {
			super();
			this.url = url;
		}

		public URL getPage() {
			return url;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (URL link : processPage(url)) {
				if (Thread.currentThread().isInterrupted())
					return;
				submitCrawlTask(link);
			}
		}

	}

	private volatile TrackingExecutor exec;
	private final Set<URL> urlsToCrawl = new HashSet<URL>();
	private final long TIMEOUT = 10;
	private final TimeUnit UNIT = TimeUnit.SECONDS;

	protected abstract List<URL> processPage(URL url);

	public synchronized void start() {
		exec = new TrackingExecutor(Executors.newCachedThreadPool());
		for (URL url : urlsToCrawl) {
			submitCrawlTask(url);
		}
		urlsToCrawl.clear();
	}

	public synchronized void stop() throws InterruptedException {
		try {
			saveUncrawled(exec.shutdownNow());
			if (exec.awaitTermination(TIMEOUT, UNIT)) {
				saveUncrawled(exec.getCancelledTasks());
			}
		} finally {
			exec = null;
		}
	}

	private void saveUncrawled(List<Runnable> uncrawled) {
		for (Runnable task : uncrawled) {
			urlsToCrawl.add(((CrawlTask) task).getPage());
		}
	}

	private void submitCrawlTask(URL u) {
		exec.execute(new CrawlTask(u));
	}

}
