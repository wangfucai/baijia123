package com.baijia123.interrupt;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public abstract class SocketUsingTask<T> implements CancellableTask<T> {
	
	private Socket socket;
	
	protected synchronized void setSocket(Socket s){
		socket = s;
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		try{
			if(socket != null){
				socket.close();
			}
		}catch(IOException ignored){
			
		}
	}

	@Override
	public RunnableFuture<T> newTask() {
		// TODO Auto-generated method stub
		return new FutureTask<T>(this){

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				// TODO Auto-generated method stub
				try{
					SocketUsingTask.this.cancel();
				}finally{
					return super.cancel(mayInterruptIfRunning);
				}
			}
			
		};
	}

}
