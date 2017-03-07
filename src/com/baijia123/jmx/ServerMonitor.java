package com.baijia123.jmx;

public class ServerMonitor implements ServerMonitorMBean {

    private final ServerImpl target;

    public ServerMonitor(ServerImpl target) {
        super();
        this.target = target;
    }

    @Override
    public long getUpTime() {
        // TODO Auto-generated method stub
        return System.currentTimeMillis() - target.startTime;
    }

}
