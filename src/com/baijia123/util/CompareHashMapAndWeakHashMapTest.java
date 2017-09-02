package com.baijia123.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class CompareHashMapAndWeakHashMapTest {
    
    private static void printIteratorAndCountMap(Map map) {
        for(Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry entry = (Map.Entry)iter.next();
            System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
        }
        System.out.println("map size = " + map.size());
    }
    
    private static class Self {
        private Integer id;

        public Self(Integer id) {
            super();
            this.id = id;
        }

        //����finalize()����,��GC����ʱ�ᱻִ��
        @Override
        protected void finalize() throws Throwable {
            // TODO Auto-generated method stub
            super.finalize();
            System.out.printf("GC self : id = %d, addr = 0x%s\n", id, this);
            
            //System.out.printf("GC Self: id=%d addr=0x%s)\n", id, this);
        }
        
        
    }
    
    private static void compareWithString() {
        /**
         * ����string s=��tt��
         * string s1=��tt��    s����s1
         * ��    string s=new string����ss����
         * string s1=new string����ss���� s������s1
         * 
         * String s="tt";
         * String ss= new String("ff") ;
         * 
         * ���ڵ�һ�ַ�ʽ��  java��һ���ַ��������� ���½�һ����ʱ���������һ���������û������ַ��� ����� ֱ�ӷ������� û�о��������½�
         * �ڶ��ַ�ʽ �½�һ�� ������Ҳ��ȥ��������߲���  ������û�� �������ڶ����½�һ��  �������������û�� ���ڻ���������Ҳ�½�һ���� 
         */
        //String w1 = "w1";//gc�����԰�w1��Ӧ��ֵɾ����
        String w1 = new String("w1");//gc���԰�w1��Ӧ��ֵɾ����
        String w2 = "w2";
        String h1 = "h1";
        String h2 = "h2";
        
        Map wmap = new WeakHashMap();
        wmap.put(w1, "w1");
        wmap.put(w2, "w2");
        
        Map hmap =  new HashMap();
        hmap.put(h1, "h1");
        hmap.put(h2, "h2");
        
        hmap.remove(h1);
        
        w1 = null;
        System.gc();
        
        System.out.println("-------------hashmap-----------");
        printIteratorAndCountMap(hmap);
        
        System.out.println("--------------weakhashmap-----------");
        printIteratorAndCountMap(wmap);
    }
    
    private static void compareWithSelf() {
        Self w1 = new Self(11);
        Self w2 = new Self(2);
        
        Self h1 = new Self(3);
        Self h2 = new Self(4);
        
        Map wmap = new WeakHashMap();
        wmap.put(w1, "w1");
        wmap.put(w2, "w2");
        
        Map hmap =  new HashMap();
        hmap.put(h1, "h1");
        hmap.put(h2, "h2");
        
        hmap.remove(h1);
        
        w1 = null;
        System.gc();
        
        System.out.println("-------------self hashmap-----------");
        printIteratorAndCountMap(hmap);
        
        System.out.println("--------------self weakhashmap-----------");
        printIteratorAndCountMap(wmap);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        compareWithString();
        
        compareWithSelf();
    }

}
