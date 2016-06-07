package com.baijia123.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Preloader {

	public class DataLoadException extends Exception {
		private static final long serialVersionUID = -646535928157135644L;

	}

	public class ProductInfo {
		
		

	}
	
	public ProductInfo loadProductInfo(){
		return new ProductInfo();
	}
	
	
	
	private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>(){

		@Override
		public ProductInfo call() throws Exception {
			// TODO Auto-generated method stub
			return loadProductInfo();
		}		
	});
	
	private final Thread thread = new Thread(future);
	
	public void start(){
		thread.start();
	}
	
	public ProductInfo get() throws DataLoadException,InterruptedException{
		try{
			return future.get();
		}catch(ExecutionException e){
			Throwable cause = e.getCause();
			if(cause instanceof DataLoadException){
				throw (DataLoadException) cause;
			}else{
				throw launderThrowable(cause);
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
