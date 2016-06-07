package com.baijia123.executor;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class OutOfTime {
	public static class ThrowTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			throw new RuntimeException();
		}

	}

	public static void main(String[] args) throws Exception{
		Timer timer = new Timer();
		timer.schedule(new ThrowTask(), 1);
		TimeUnit.SECONDS.sleep(1);
		timer.schedule(new ThrowTask(), 1);
		TimeUnit.SECONDS.sleep(5);
	}
}
