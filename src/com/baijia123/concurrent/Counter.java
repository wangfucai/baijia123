package com.baijia123.concurrent;

public class Counter {

	public static volatile int count = 0;

	public static void inc() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count++;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Counter.inc();
				}

			}).start();
		}

		System.out.println("运行结果:Counter.count=" + Counter.count);
	}
}
