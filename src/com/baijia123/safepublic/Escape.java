package com.baijia123.safepublic;

/**
 * �ڲ����ʵ�������˶Է�װʵ�����������ã������ڶ���û�б���ȷ����֮ǰ���ͻᱻ�������п��ܻ��в���ȫ���ء� 
 * һ����ȷ�����Ķ������ͨ������������ȫ������
 * 1��ͨ����̬��ʼ������ʼ���������á� 
 * 2����������������ô洢��volatile����߾���ԭ���Ե����У��磺java5.0�е�AtomicReference���� 
 * 3���������������ô�ŵ���ȷ�����Ķ����final���С� 
 * 4���������������ô�ŵ��������������У��磺ͬ�������������� 
 * 
 *  ���Ҫ����һ������̬�����Ķ�����򵥵ķ�ʽ����ʹ�þ�̬��ʼ������
 *  �����������ʾ��public static Holder holder=new Holder();
 *  ��̬��ʼ������JVM�����ʼ��ʱִ�У�JVM��ִ�о�̬�����ĳ�ʼ��ʱ��������ͬ��������
 *  ��˿��Ա�֤����İ�ȫ������
 * @author admin
 *
 */
public class Escape {
	private class InnerClass {

		public InnerClass() {
			// ���������Escape������ɹ���ǰ��ǰ���õ�Escape��private����
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
