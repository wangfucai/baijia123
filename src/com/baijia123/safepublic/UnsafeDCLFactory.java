package com.baijia123.safepublic;

/**
 * Double-Checked Locking
 * @author admin
 * 这段代码其实在多线程环境并没有安全发布。所谓安全发布需要以下几个约束：
 * 1. 使用静态的初始化器进行初始化
 * 2.通过一个适当加锁字段交换引用
 * 3.通过一个volatile或AtomicX字段交换引用 
 * 4.使用final标识字段，initialize the value into a final field (JLS 17.5). 
 * Caveat emptor:只对之前没有其他人看到过的对象有效，对于已经发布的对象使用final已经太迟了。
 * 
 * 对于UnsafeDCLFactory显然有以下问题：
 * 1.并没有使用静态初始化器
 * 2.至少有一个单例读是不受锁保护的
 * 3.单例没有通过volatile 字段发布
 * 4.单例也没有通过final字段发布.
 *
 *
 */

public class UnsafeDCLFactory {

	// 问题在这里，应该让其他线程看到instance是否被初始化
	private Singleton instance;

	public Singleton get() {
		if (instance == null) { // check 1
			synchronized (this) {
				if (instance == null) { // check 2
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

}
