package com.baijia123.interrupt;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

public interface CancellableTask<T> extends Callable<T> {

	void cancel();

	RunnableFuture<T> newTask();
}
