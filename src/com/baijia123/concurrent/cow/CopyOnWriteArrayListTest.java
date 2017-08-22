package com.baijia123.concurrent.cow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * 从一开始大家都在共享同一个内容，当某个人想要修改这个内容的时候，才会真正把内容Copy出去形成一个新的内容然后再改，这是一种延时懒惰策略.
 * CopyOnWrite容器即写时复制的容器
 * 
 * CopyOnWrite容器有很多优点，但是同时也存在两个问题，即内存占用问题和数据一致性问题 1)内存占用问题。因为CopyOnWrite的写时复制机制，所以在进行写操作的时候，内存里会同时驻扎两个对象的内存，
 * 旧的对象和新写入的对象（注意:在复制的时候只是复制容器里的引用，只是在写的时候会创建新对象添加到新容器里， 而旧容器的对象还在使用，所以有两份对象内存）。如果这些对象占用的内存比较大，比如说200M左右， 那么再写入100M数据进去，内存就会占用300M，那么这个时候很有可能造成频繁的Yong
 * GC和Full GC。 之前我们系统中使用了一个服务由于每晚使用CopyOnWrite机制更新大对象，造成了每晚15秒的Full GC，应用响应时间也随之变长。 针对内存占用问题，可以通过压缩容器中的元素的方法来减少大对象的内存消耗，比如，如果元素全是10进制的数字，
 * 可以考虑把它压缩成36进制或64进制。或者不使用CopyOnWrite容器，而使用其他的并发容器，如ConcurrentHashMap。
 * 
 * 2)数据一致性问题。CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性。 所以如果你希望写入的的数据，马上能读到，请不要使用CopyOnWrite容器。
 * 
 * @author WangFuCai
 *
 */
public class CopyOnWriteArrayListTest {

    /**
     * 从结果很容易就看出来，hashcode变化了多次， * 说明了list已经不是原来的list对象了。 这说明了CopyOnWriteArrayList类的add函数在执行的时候确实是修改了list的数组对象。
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        List<String> a = new ArrayList<String>();
        a.add("a");
        a.add("b");
        a.add("c");
        final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>(a);
        //运行的时候就会抛出java.util.ConcurrentModificationException错误。这是因为主线程在遍历list的时候，子线程在向list中添加元素。
        //final ArrayList<String> list = new ArrayList<String>(a);
        Thread t = new Thread(new Runnable() {
            int count = -1;

            @Override
            public void run() {
                while (true) {
                    list.add(count++ + "");
                }
            }
        });
        t.setDaemon(true);
        t.start();
        Thread.currentThread().sleep(3);
        for (String s : list) {
            System.out.println(list.hashCode());
            System.out.println(s);
        }
    }

}
