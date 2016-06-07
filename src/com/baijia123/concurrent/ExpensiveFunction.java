package com.baijia123.concurrent;

import java.math.BigInteger;

public class ExpensiveFunction implements Compute<String, BigInteger> {

	@Override
	public BigInteger compute(String arg) throws InterruptedException {
		// TODO Auto-generated method stub
		return new BigInteger(arg);
	}

}
