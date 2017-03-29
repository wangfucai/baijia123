package com.baijia123.safepublic;

@ThreadSafe
public class CheesyCounter {
	private volatile int value;

	public int getValue() {
		return value;
	}

	public synchronized int increment() {
		return value++;
	}
}
