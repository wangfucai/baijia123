package com.baijia123.interrupt;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrimeGeneratorTest {

	public static List<BigInteger> aSecondeOfPrimes() throws InterruptedException {
		PrimeGenerator generator = new PrimeGenerator();
		new Thread(generator).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} finally {
			generator.cancel();
		}

		return generator.get();
	}
	
	public static void main(String[] args) throws InterruptedException{
		aSecondeOfPrimes();
	}
}
