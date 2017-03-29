package com.baijia123.safepublic;

/**
 * 内部类的实例包含了对封装实例隐含的引用，这样在对象没有被正确构造之前，就会被发布，有可能会有不安全因素。 
 * 一个正确创建的对象可以通过下列条件安全发布：
 * 1、通过静态初始化器初始化对象引用。 
 * 2、将发布对象的引用存储到volatile域或者具有原子性的域中（如：java5.0中的AtomicReference）。 
 * 3、将发布对象引用存放到正确创建的对象的final域中。 
 * 4、将发布对象引用存放到由锁保护的域中（如：同步化的容器）。 
 * 
 *  如果要发布一个被静态创建的对象，最简单的方式就是使用静态初始化器，
 *  如下面代码所示：public static Holder holder=new Holder();
 *  静态初始化器由JVM在类初始化时执行，JVM在执行静态变量的初始化时会有内在同步保护，
 *  因此可以保证对象的安全发布。
 * @author admin
 *
 */
public class Escape {
	private class InnerClass {

		public InnerClass() {
			// 这里可以在Escape对象完成构造前提前引用到Escape的private变量
			System.out.println(Escape.this.thisCanBeEscape);
			// TODO Auto-generated constructor stub
		}

	}

	private int thisCanBeEscape = 0;

	public Escape() {
		new InnerClass();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		new Escape();
	}
	
	

}
