package com.baijia123.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * ����POJO-MBean����JMX��ͨ��ע����ʵ��һЩ�����Ĳ��裬�ö�̬MBean������ô���ۡ�
 * @author WangFuCai
 *
 */
public class DynamicServerManager {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("agent:name=test");
        TestDynamic testMBean = new TestDynamic();
        mBeanServer.registerMBean(testMBean, name);
        Thread.sleep(5000000);
    }

}
