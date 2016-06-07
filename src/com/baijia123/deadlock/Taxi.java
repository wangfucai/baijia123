package com.baijia123.deadlock;

public class Taxi {
	private Point location, destination;
	private final Dispatcher dispatcher;

	public Taxi(Dispatcher dispatcher) {
		super();
		this.dispatcher = dispatcher;
	}

	public synchronized Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		boolean reachedDestination;
		synchronized (this) {
			this.location = location;
			reachedDestination = location.equals(destination);
		}
		if (reachedDestination) {
			dispatcher.notifyAvailable(this);
		}
	}
}
