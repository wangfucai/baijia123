package com.baijia123.interrupt;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimedRun2 {
	
	private static final ExecutorService taskExec = Executors.newFixedThreadPool(5);
	
	public static void timedRun(final Runnable r, long timeout, TimeUnit unit)
			throws InterruptedException {
		Future<?> task = taskExec.submit(r);
		try{
			task.get(timeout, unit);
		}catch(TimeoutException e){
			
		}catch(ExecutionException e){
			launderThrowable(e.getCause());
		}finally{
			task.cancel(true);
		}
	}
	
	public static RuntimeException launderThrowable(Throwable t) {
		if (t instanceof RuntimeException)
			return (RuntimeException) t;
		else if (t instanceof Error)
			throw (Error) t;
		else
			throw new IllegalStateException("Not unchecked", t);
	}
}
