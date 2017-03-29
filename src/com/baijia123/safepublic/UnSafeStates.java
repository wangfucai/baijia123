package com.baijia123.safepublic;

import java.util.Arrays;

public class UnSafeStates {

	private String[] states = { "AK", "AL" };

	public String[] getStates() {
		return states;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UnSafeStates safe = new UnSafeStates();
		System.out.println(Arrays.toString(safe.getStates()));
		safe.getStates()[1] = "c";
		System.out.println(Arrays.toString(safe.getStates()));
	}

}
