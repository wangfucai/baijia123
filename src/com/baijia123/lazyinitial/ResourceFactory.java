package com.baijia123.lazyinitial;

//ʹ���ӳٳ�ʼ��ռλ(Holder)��ģʽʹ��һ��ר�ŵ�������ʼ��Resource��JVM���Ƴ�ResourceHolder�ĳ�ʼ��
//������֪����ʼʹ�������ʱ�ų�ʼ������������ͨ��һ����̬��ʼ������ʼ��Resource����Ϊ����Ҫ�����ͬ����
//���κ�һ���̵߳�һ�ε���getRerouceʱ������ʹResourceHolder�����غͳ�ʼ������ʱ��̬��ʼ������ִ��
//Resource�ĳ�ʼ������
public class ResourceFactory {
    private static class ResourceHolder {
        public static Resource resource = new Resource();
    }

    public static Resource getResource() {
        return ResourceHolder.resource;
    }
}
