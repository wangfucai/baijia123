package com.baijia123.safepublic;

public class FinalWrapperFactory {
	private static class FinalWrapper {
		public final Singleton instance;

		public FinalWrapper(Singleton instance) {
			this.instance = instance;
		}
	}

	private FinalWrapper wrapper;

	public Singleton get() {
		FinalWrapper w = wrapper;
		if (w == null) {
			synchronized (this) {
				w = wrapper;
				if (w == null) {
					w = new FinalWrapper(new Singleton());
					wrapper = w;
				}
			}
		}
		return w.instance;
	}
}
