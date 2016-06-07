package com.baijia123.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memoizer<A, V> implements Compute<A, V> {
	
	private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<A,Future<V>>();
	private final Compute<A,V> c;
	
	public Memoizer(Compute<A,V> c){
		this.c = c;
	}

	@Override
	public V compute(A arg) throws InterruptedException {
		// TODO Auto-generated method stub
		while(true){
			Future<V> f = cache.get(arg);
			if(f == null){
				Callable<V> eval = new Callable<V>(){

					@Override
					public V call() throws Exception {
						// TODO Auto-generated method stub
						return c.compute(arg);
					}
					
				};
				FutureTask<V> ft = new FutureTask<V>(eval);
				f = cache.putIfAbsent(arg, ft);
				if(f == null){
					f = ft;
					ft.run();
				}
			}
			try{
				return f.get();
			}catch(CancellationException e){
				cache.remove(arg, f);
			}catch(ExecutionException e){
				launderThrowable(e.getCause());
			}
		}
	}
	
	public RuntimeException launderThrowable(Throwable t){
		if(t instanceof RuntimeException)
			return (RuntimeException)t;
		else if(t instanceof Error)
			throw (Error) t;
		else 
			throw new IllegalStateException("Not unchecked", t);
	}

}
