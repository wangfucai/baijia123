package com.baijia123.interrupt;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimedRun1 {
	private static final ScheduledExecutorService cancelExec = Executors
			.newScheduledThreadPool(5);

	public static void timedRun(final Runnable r, long timeout, TimeUnit unit)
			throws InterruptedException {
		class RethrowableTask implements Runnable {
			private volatile Throwable t;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					r.run();
				} catch (Throwable t) {
					this.t = t;
				}
			}

			public void rethrow() {
				if (t != null) {
					throw launderThrowable(t);
				}
			}

			public RuntimeException launderThrowable(Throwable t) {
				if (t instanceof RuntimeException)
					return (RuntimeException) t;
				else if (t instanceof Error)
					throw (Error) t;
				else
					throw new IllegalStateException("Not unchecked", t);
			}

		}
		RethrowableTask task = new RethrowableTask();
		final Thread taskThread = new Thread(task);
		taskThread.start();
		cancelExec.schedule(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				taskThread.interrupt();
			}

		}, timeout, unit);
		taskThread.join(unit.toMillis(timeout));
		task.rethrow();
	}
}
