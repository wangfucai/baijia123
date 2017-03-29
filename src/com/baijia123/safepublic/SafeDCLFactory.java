package com.baijia123.safepublic;

public class SafeDCLFactory {
	private volatile Singleton instance;

	public Singleton get() {
		if (instance == null) { // check 1
			synchronized (this) {
				if (instance == null) { // check 2
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
}
