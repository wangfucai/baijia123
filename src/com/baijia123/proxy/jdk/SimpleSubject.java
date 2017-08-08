package com.baijia123.proxy.jdk;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleSubject implements Subject {

    private Map<String, String> msg = new ConcurrentHashMap<String, String>();

    @Override
    public void pub(String key, String content) {
        // TODO Auto-generated method stub
        System.out.println("pub msg: key is " + key + ", content is " + content);
        msg.put(key, content);
    }

    @Override
    public String sub(String key) {
        // TODO Auto-generated method stub
        if (msg.containsKey(key)) {
            String ret = msg.get(key);
            System.out.println("sub msg: key is " + key + ", result is " + ret);
            return ret;
        }

        return null;
    }

}
