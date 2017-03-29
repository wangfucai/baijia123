package com.baijia123.safepublic;

/**
 * Double-Checked Locking
 * @author admin
 * ��δ�����ʵ�ڶ��̻߳�����û�а�ȫ��������ν��ȫ������Ҫ���¼���Լ����
 * 1. ʹ�þ�̬�ĳ�ʼ�������г�ʼ��
 * 2.ͨ��һ���ʵ������ֶν�������
 * 3.ͨ��һ��volatile��AtomicX�ֶν������� 
 * 4.ʹ��final��ʶ�ֶΣ�initialize the value into a final field (JLS 17.5). 
 * Caveat emptor:ֻ��֮ǰû�������˿������Ķ�����Ч�������Ѿ������Ķ���ʹ��final�Ѿ�̫���ˡ�
 * 
 * ����UnsafeDCLFactory��Ȼ���������⣺
 * 1.��û��ʹ�þ�̬��ʼ����
 * 2.������һ���������ǲ�����������
 * 3.����û��ͨ��volatile �ֶη���
 * 4.����Ҳû��ͨ��final�ֶη���.
 *
 *
 */

public class UnsafeDCLFactory {

	// ���������Ӧ���������߳̿���instance�Ƿ񱻳�ʼ��
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
