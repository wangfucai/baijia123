package com.baijia123.guava.basicutil;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

public class Foo implements Comparable<Foo> {

    @Nullable
    private String sortedBy;
    private int notSortedBy;

    private Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {

        @Override
        public String apply(Foo input) {
            // TODO Auto-generated method stub
            return input.sortedBy;
        }
    });

    public Foo(String sortedBy, int notSortedBy) {
        super();
        this.sortedBy = sortedBy;
        this.notSortedBy = notSortedBy;
    }

    @Override
    public int compareTo(Foo o) {
        // TODO Auto-generated method stub
        return ordering.compare(this, o);
    }

}
