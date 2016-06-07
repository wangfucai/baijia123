package com.baijia123.interrupt;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogService1 {

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
				while (true) {
					try {
						synchronized (LogService1.this) {
							if (isShutdown && reservations == 0) {
								break;
							}
						}
						String msg = queue.take();
						writer.println(msg);
						synchronized (LogService1.this) {
							--reservations;
						}
					} catch (InterruptedException e) {

					}
				}
			} finally {
				writer.close();
			}
		}

	}

	private final BlockingQueue<String> queue;
	private final LoggerThread loggerThread;
	private boolean isShutdown;
	private int reservations;

	public LogService1(Writer writer) {
		this.queue = new LinkedBlockingQueue<String>(5);
		this.loggerThread = new LoggerThread(writer);
		isShutdown = false;
		reservations = 0;
	}

	public void start() {
		loggerThread.start();
	}

	public void stop() {
		synchronized (this) {
			isShutdown = true;
		}
		loggerThread.interrupt();
	}

	public void log(String msg) throws InterruptedException {
		synchronized (this) {
			if (isShutdown) {
				throw new IllegalStateException();
			}
			++reservations;
		}
		queue.put(msg);
	}

}
