package com.baijia123.proxy.jdk;

public interface Subject {
    void pub(String key, String content);
    
    String sub(String key);
}
