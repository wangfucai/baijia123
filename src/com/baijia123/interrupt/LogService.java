package com.baijia123.interrupt;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class LogService {

	private class WriterTask implements Runnable {
		private final String msg;

		public WriterTask(String msg) {
			super();
			this.msg = msg;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

		}

	}

	private final ExecutorService exec = Executors.newSingleThreadExecutor();
	private final long TIMEOUT = 100;
	private final TimeUnit UNIT = TimeUnit.SECONDS;
	private final PrintWriter writer;

	public LogService(Writer writer) {
		this.writer = new PrintWriter(writer);
	}

	public void start() {

	}

	public void log(String msg) {
		try {
			exec.execute(new WriterTask(msg));
		} catch (RejectedExecutionException ignored) {

		}
	}

	public void stop() throws InterruptedException {
		try {
			exec.shutdown();
			exec.awaitTermination(TIMEOUT, UNIT);
		} finally {
			writer.close();
		}
	}
}
