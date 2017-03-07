package com.baijia123.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * ManagementFactory.getPlatformMBeanServer() returns a reference to the existing MBean server within the JVM. JConsole looks at the beans on that
 * server. If you use createMBeanServer(), that will create an entirely new server. JConsole has no knowledge of it, and so will not see the beans
 * registered with it.
 * 
 * @author WangFuCai
 *
 */
public class ServerManager {

    private static ObjectName objectName;
    private static MBeanServer mBeanServer;

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        init();
        while (true) {

        }
        // Thread.sleep(50 * 1000);
        // manage();
    }

    private static void init() throws Exception {
        ServerImpl serverImpl = new ServerImpl();
        ServerMonitor serverMonitor = new ServerMonitor(serverImpl);
        // mBeanServer = MBeanServerFactory.createMBeanServer();
        mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // objectName = new ObjectName("objectName:id=ServerMonitor1");
        objectName = new ObjectName("agent:name=test1");
        mBeanServer.registerMBean(serverMonitor, objectName);
    }

    private static void manage() throws Exception {
        Long upTime = (Long) mBeanServer.getAttribute(objectName, "UpTime");
        System.out.println(upTime);

    }

}
