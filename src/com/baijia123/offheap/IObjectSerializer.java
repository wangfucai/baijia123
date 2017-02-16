package com.baijia123.offheap;

public interface IObjectSerializer {
    
    <T> byte[] serialize(T obj);

    <T> T deserialize(byte[] bytes);
}
