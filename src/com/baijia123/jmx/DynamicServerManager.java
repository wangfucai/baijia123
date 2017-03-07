package com.baijia123.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * 基于POJO-MBean来做JMX，通过注解来实现一些繁琐的步骤，让动态MBean不在那么蛋疼。
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
