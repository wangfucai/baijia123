package com.baijia123.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Memoizer2<A, V> implements Compute<A, V> {
	
	private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
	private final Compute<A,V> c;
	
	public Memoizer2(Compute<A,V> c){
		this.c = c;
	}

	@Override
	public V compute(A arg) throws InterruptedException {
		// TODO Auto-generated method stub
		V result = cache.get(arg);
		if(result == null){
			result = c.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}

}
