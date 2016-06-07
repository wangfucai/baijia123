package com.baijia123.executor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class LifecycleWebServer {
	private final ExecutorService exec = Executors.newFixedThreadPool(10);
	
	public void start() throws IOException{
		ServerSocket serverSocket = new ServerSocket(80);
		while(!exec.isShutdown()){
			try{
				final Socket conn = serverSocket.accept();
				exec.execute(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						handleReuqest(conn);
					}
				});
			}catch(RejectedExecutionException e){
				if(!exec.isShutdown()){
					System.out.println("task submission rejected");
				}
			}
		}
	}
	
	public void stop(){
		exec.shutdown();
	}
	
	void handleReuqest(Socket connection){
//		Request req = readRequest(connection);
//		if(isShutdownRequest(req))
//			stop();
//		else
//			dispatchRequest(req);
		stop();
	}
}
