package com.baijia123.test;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack<E> {

    private AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();

    private class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            super();
            this.item = item;
            this.next = next;
        }

    }

    public boolean push(E e) {
        Node<E> newNode = new Node<E>(e, null);
        Node<E> oldNode;
        do {
            oldNode = top.get();
            newNode.next = oldNode;
        } while (!top.compareAndSet(oldNode, newNode));
        return true;
    }

    public E pop() {
        Node<E> newNode;
        Node<E> oldNode;
        do {
            oldNode = top.get();
            if (oldNode == null)
                return null;
            newNode = oldNode.next;
        } while (!top.compareAndSet(oldNode, newNode));
        return oldNode.item;
    }

}
