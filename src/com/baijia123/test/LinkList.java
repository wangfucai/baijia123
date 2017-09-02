package com.baijia123.test;

import java.util.Collection;

/**
 * 
 * @author WangFuCai
 *
 * @param <E>
 */
public class LinkList<E> {

    private Node<E> head;
    private Node<E> tail;

    private int size;

    public LinkList() {
        super();
        // TODO Auto-generated constructor stub
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
        Node<E> next;

        public Node(E item, Node<E> prev, Node<E> next) {
            super();
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

    }

    public boolean add(E item) {
        final Node<E> t = tail;
        Node<E> newNode = new Node<>(item, t, null);
        tail = newNode;
        if (t == null) {
            head = newNode;
        } else {
            t.next = newNode;
        }
        size++;
        return false;
    }

    private Node<E> node(int index) {
        if (index < size >> 1) {
            Node<E> node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = tail;
            for (int i = size - 1; i > index; i--) {
                node = tail.prev;
            }
            return node;
        }
    }

    public boolean addAll(Collection<? extends E> c) {
        E[] a = (E[]) c.toArray();
        int aLen = a.length;
        if (aLen == 0)
            return false;
        Node<E> prev = tail;
        Node<E> succ = null;
        for (E o : a) {
            Node<E> newNode = new Node<E>(o, prev, null);
            if (prev == null) {
                head = newNode;
            } else {
                prev.next = newNode;
            }
            prev = newNode;
        }

        size += aLen;
        return false;
    }

    private E unlink(Node<E> node) {
        final E item = node.item;
        final Node<E> prev = node.prev;
        final Node<E> next = node.next;
        if (prev == null) {
            head = node;
        } else {
            next.prev = prev;
            node.prev = null;
        }
        if (next == null) {
            tail = prev;
        } else {
            prev.next = next;
            node.next = null; // help GC
        }
        node.item = null;
        size--;
        return item;
    }

    public boolean remove(E object) {
        if (object == null) {
            for (Node<E> node = head; node != null; node = node.next) {
                if (node.item == null) {
                    unlink(node);
                    return true;
                }
            }
        } else {
            for (Node<E> node = head; node != null; node = node.next) {
                if (object.equals(node.item)) {
                    unlink(node);
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
