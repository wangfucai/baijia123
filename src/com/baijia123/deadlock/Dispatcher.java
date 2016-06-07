package com.baijia123.deadlock;

import java.util.HashSet;
import java.util.Set;

public class Dispatcher {

	public class Image {
		public void drawMarker(Point x) {
		}
	}

	private final Set<Taxi> taxis;
	private final Set<Taxi> availableTaxis;

	public Dispatcher() {
		taxis = new HashSet<Taxi>();
		availableTaxis = new HashSet<Taxi>();
	}

	public synchronized void notifyAvailable(Taxi taxi) {
		availableTaxis.add(taxi);
	}

	public Image getImage() {
		Set<Taxi> copy;
		synchronized (this) {
			copy = new HashSet<Taxi>(taxis);
		}
		Image image = new Image();
		for (Taxi t : copy) {
			image.drawMarker(t.getLocation());
		}
		return image;
	}
}
