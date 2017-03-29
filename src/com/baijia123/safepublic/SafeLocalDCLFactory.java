package com.baijia123.safepublic;

public class SafeLocalDCLFactory {
	private volatile Singleton instance;

	public Singleton getInstance() {
		Singleton res = instance;
		if (res == null) {
			synchronized (this) {
				if (res == null) {
					instance = new Singleton();

				}
			}
			return instance;
		}
		return res;
	}
}
