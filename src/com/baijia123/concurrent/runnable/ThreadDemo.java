package com.baijia123.concurrent.runnable;

/**
 * ͨ���˴����ҵ�Thread�е�start()�����Ķ��壬���Է��ִ˷�����ʹ����private native void start0();
 * ����native�ؼ��ֱ�ʾ���Ե��ò���ϵͳ�ĵײ㺯������ô�����ļ�����ΪJNI����(Java Native Interface)
 * start������һ���̣߳�run���ڱ��߳̽��в���
 * @author WangFuCai
 *
 */
public class ThreadDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyThread mt1 = new MyThread("�߳�1");
        MyThread mt2 = new MyThread("�߳�2");
        mt1.start();
        mt2.start();
        //mt1.run();
        //mt2.run();
    }

}
