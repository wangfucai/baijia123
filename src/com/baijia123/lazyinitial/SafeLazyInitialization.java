package com.baijia123.lazyinitial;

public class SafeLazyInitialization {
    private static Resource resource;

    //����synchronized���������������������ڹ���ʱ��״̬����������ǿɱ�ģ���ô�ڶ��̺߳�д�߳�֮��
    //��Ȼ��Ҫͨ��ͬ����ȷ�������޸Ĳ����ǿɼ��ģ��Լ����������ƻ�
    public synchronized static Resource getInstance() {
        if (resource == null) {
            resource = new Resource();
        }
        return resource;
    }
}
