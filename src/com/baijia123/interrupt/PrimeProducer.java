package com.baijia123.interrupt;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class PrimeProducer extends Thread {
	
	private final BlockingQueue<BigInteger> queue;

	public PrimeProducer(BlockingQueue<BigInteger> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			BigInteger p = BigInteger.ONE;
			while(!Thread.currentThread().isInterrupted())
				queue.put(p.nextProbablePrime());
		}catch(InterruptedException e){
			
		}
	}
	
	public void cancel(){
		interrupt();
	}

}
