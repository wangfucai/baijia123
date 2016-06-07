package com.baijia123.interrupt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TrackingExecutor extends AbstractExecutorService {

	private final ExecutorService exec;
	private final Set<Runnable> tasksCancelledAtShutdown = Collections
			.synchronizedSet(new HashSet<Runnable>());

	public TrackingExecutor(ExecutorService exec) {
		super();
		this.exec = exec;
	}

	public List<Runnable> getCancelledTasks() {
		if (!exec.isTerminated()) {
			throw new IllegalStateException();
		}
		return new ArrayList<Runnable>(tasksCancelledAtShutdown);
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Runnable> shutdownNow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isShutdown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute(Runnable runnable) {
		// TODO Auto-generated method stub
		exec.execute(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					runnable.run();
				} finally {
					if (isShutdown() && Thread.currentThread().isInterrupted()) {
						tasksCancelledAtShutdown.add(runnable);
					}
				}
			}

		});
	}

}
