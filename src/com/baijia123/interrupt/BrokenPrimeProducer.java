package com.baijia123.interrupt;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class BrokenPrimeProducer extends Thread {

	private final BlockingQueue<BigInteger> queue;
	private volatile boolean cancelled = false;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			BigInteger p = BigInteger.ONE;
			while (!cancelled)
				queue.put(p = p.nextProbablePrime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void cancel() {
		cancelled = true;
	}

	public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
		super();
		this.queue = queue;
	}

}
