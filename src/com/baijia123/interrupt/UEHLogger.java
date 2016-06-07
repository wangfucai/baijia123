package com.baijia123.interrupt;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UEHLogger implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		// TODO Auto-generated method stub
		Logger logger = Logger.getAnonymousLogger();
		logger.log(Level.SEVERE,
				"Thread terminated with exception: " + t.getName(), e);
	}

}
