package com.baijia123.concurrent;

public interface Compute<A, V> {
	
	V compute(A arg) throws InterruptedException;
}
