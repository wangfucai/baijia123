package com.baijia123.safepublic;

/**
 * 使用初始化器发布单例的方式，通常我们使用下面静态初始化器来实现（单例的另外一种实现）
 * 这段代码直到第一次调用get()方法，Holder才被初始化
 * 但是因为是在构造器以外使用了final字段，这太迟了，得用一个wrapper包裹一下，以确保没有人看到过单例：
 * @author admin
 *
 */

public class HolderFactory {

	public Singleton get() {
		return Holder.instance;
	}

	private static class Holder {
		public static final Singleton instance = new Singleton();
	}
}
