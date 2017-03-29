package com.baijia123.safepublic;

/**
 * ʹ�ó�ʼ�������������ķ�ʽ��ͨ������ʹ�����澲̬��ʼ������ʵ�֣�����������һ��ʵ�֣�
 * ��δ���ֱ����һ�ε���get()������Holder�ű���ʼ��
 * ������Ϊ���ڹ���������ʹ����final�ֶΣ���̫���ˣ�����һ��wrapper����һ�£���ȷ��û���˿�����������
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
