package com.baijia123.interrupt;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BrokenPrimeComsumer {
	void comsumePrimes() throws InterruptedException {
		BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<BigInteger>(
				10);
		BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
		producer.start();
		try {
			while (needMorePrimes()) {
				comsume(primes.take());
			}
		} finally {
			producer.cancel();
		}
	}

	private boolean needMorePrimes() {
		return true;
	}

	private void comsume(BigInteger prime) {

	}
}
