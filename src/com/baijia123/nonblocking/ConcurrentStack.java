package com.baijia123.nonblocking;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用 Treiber 算法的非阻塞堆栈
 * 
 * @author WangFuCai
 *
 * @param <E>
 */
public class ConcurrentStack<E> {
    AtomicReference<Node<E>> head = new AtomicReference<Node<E>>();

    public void push(E item) {
        Node<E> newHead = new Node<E>(item);
        Node<E> oldHead;
        do {
            oldHead = head.get();
            newHead.next = oldHead;
        } while (!head.compareAndSet(oldHead, newHead));
    }

    public E pop() {
        Node<E> oldHead;
        Node<E> newHead;
        do {
            oldHead = head.get();
            if (oldHead == null)
                return null;
            newHead = oldHead.next;
        } while (!head.compareAndSet(oldHead, newHead));
        return oldHead.item;
    }

    static class Node<E> {
        final E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }
    
    public static void main(String[] args) {
        ConcurrentStack<Integer> stack = new ConcurrentStack<>();
        stack.push(3);
        System.out.println(stack.pop());
    }
}
