package com.baijia123.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CellularAutomata {
	public class Worker implements Runnable {
		
		private final Board board;
		
		public Worker(Board board){
			this.board = board;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(!board.hasConverged()){
				for(int x = 0 ; x < board.getMaxX(); x ++){
					for(int y = 0; y < board.getMaxY(); y ++){
						board.setNewValue(x, y, 0);
					}
				}
				try{
					barrier.await();
				}catch(InterruptedException ex){
					return;
				}catch (BrokenBarrierException e) {
					// TODO: handle exception
					return;
				}
			}
		}

	}

	public class Board {

		public void commitNewValues(){}
		
		public Board getSubBoard(int count, int index){return new Board();}
		
		public boolean hasConverged(){return true;}
		
		public int getMaxX(){return 0;}
		
		public int getMaxY(){return 0;}
		
		public void setNewValue(int x, int y, int value){};
		
		public void waitForConvergence(){}
	}

	private final Board mainBoard;
	private final CyclicBarrier barrier;
	private final Worker[] workers;
	
	public CellularAutomata(Board board){
		this.mainBoard = board;
		int count = Runtime.getRuntime().availableProcessors();
		this.barrier = new CyclicBarrier(count, new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mainBoard.commitNewValues();
			}
		});
		this.workers = new Worker[count];
		for(int i = 0 ; i < count ; i ++){
			workers[i] = new Worker(mainBoard.getSubBoard(count, i));
		}
	}
	
	public void start(){
		for(int i = 0; i < workers.length; i ++){
			new Thread(workers[i]).start();
		}
		mainBoard.waitForConvergence();
	}
}
