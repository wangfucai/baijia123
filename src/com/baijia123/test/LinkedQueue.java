package com.baijia123.test;

import java.util.concurrent.atomic.AtomicReference;

public class LinkedQueue<E> {

    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, AtomicReference<Node<E>> next) {
            super();
            this.item = item;
            this.next = next;
        }
    }

    private final Node<E> dummy = new Node<E>(null, null);
    private final AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(dummy);
    private final AtomicReference<Node<E>> tail = new AtomicReference<Node<E>>(dummy);

    public boolean put(E item) {
        Node<E> newNode = new Node<E>(item, null);
        while (true) {
            Node<E> currTail = tail.get();
            Node<E> nextTail = currTail.next.get();
            if (currTail == tail.get()) {
                if (nextTail != null) {
                    tail.compareAndSet(currTail, nextTail);
                } else {
                    if (currTail.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(currTail, newNode);
                        return true;
                    }
                }
            }
        }
    }
}
