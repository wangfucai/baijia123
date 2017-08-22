package com.baijia123.concurrent.cow;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * �ο�CopyOnWriteArrayList��ʵ��CopyOnWriteMap
 * @author WangFuCai
 *
 *ʹ��CopyOnWriteMap��Ҫע���������飺
 *1. �������ݿ���������ʵ����Ҫ����ʼ��CopyOnWriteMap�Ĵ�С������дʱCopyOnWriteMap���ݵĿ�����
 *2. ʹ��������ӡ���Ϊÿ����ӣ�����ÿ�ζ�����и��ƣ����Լ�����Ӵ��������Լ��������ĸ��ƴ�������ʹ������������addBlackList������
 * @param <K>
 * @param <V>
 */
public class CopyOnWriteMap<K, V> implements Map<K, V>, Cloneable {

    private volatile Map<K, V> internalMap;

    public CopyOnWriteMap() {
        internalMap = new HashMap<K, V>();
    }

    public CopyOnWriteMap(int i) {
        // TODO Auto-generated constructor stub
        internalMap = new HashMap<K, V>(i);
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public V get(Object key) {
        // TODO Auto-generated method stub
        return internalMap.get(key);
    }

    @Override
    public V put(K key, V value) {
        // TODO Auto-generated method stub
        synchronized (this) {
            Map<K, V> newMap = new HashMap<K, V>(internalMap);
            V val = newMap.put(key, value);
            internalMap = newMap;
            return val;
        }
    }

    @Override
    public V remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // TODO Auto-generated method stub
        synchronized (this) {
            Map<K, V> newMap = new HashMap<K, V>(internalMap);
            newMap.putAll(m);
            internalMap = newMap;
        }
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<K> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<V> values() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

}
