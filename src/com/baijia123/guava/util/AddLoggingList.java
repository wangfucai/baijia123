package com.baijia123.guava.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ForwardingList;

public class AddLoggingList<E> extends ForwardingList<E> {
    
    private final List<E> delegate = new ArrayList<E>();

    @Override
    protected List<E> delegate() {
        // TODO Auto-generated method stub
        return delegate;
    }

    @Override
    public void add(int index, E element) {
        // TODO Auto-generated method stub
        System.out.println("add element");
        super.add(index, element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> elements) {
        // TODO Auto-generated method stub
        System.out.println("add all");
        return super.addAll(index, elements);
    }
    
    

}
