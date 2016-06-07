package com.baijia123.interrupt;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogWriter {
	private class LoggerThread extends Thread {

		private final PrintWriter writer;

		public LoggerThread(Writer writer) {
			super();
			this.writer = new PrintWriter(writer);
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				while (true)
					writer.println(queue.take());
			} catch (InterruptedException ignored) {

			} finally {
				writer.close();
			}
		}

	}

	private final BlockingQueue<String> queue;
	private final LoggerThread logger;

	public LogWriter(Writer writer) {
		this.queue = new LinkedBlockingQueue<String>(5);
		this.logger = new LoggerThread(writer);
	}

	public void start() {
		logger.start();
	}

	public void log(String msg) throws InterruptedException {
		queue.put(msg);
	}
}
