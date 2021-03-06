package com.baijia123.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

public class BoundedExecutor {

	private final Executor exec;
	private final Semaphore semaphore;

	public BoundedExecutor(Executor exec, int bound) {
		super();
		this.exec = exec;
		this.semaphore = new Semaphore(bound);
	}

	public void submitTask(final Runnable command) throws InterruptedException {
		semaphore.acquire();
		try {
			exec.execute(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						command.run();
					} finally {
						semaphore.release();
					}
				}

			});
		} catch (RejectedExecutionException e) {
			semaphore.release();
		}
	}

}
