package com.baijia123.util;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 * 
 * 参考链接http://blog.csdn.net/speedme/article/details/22485681
 * 
 * @author WangFuCai
 *
 */
public class HashSetTest {

    public static class Name {
        private String first;
        private String last;

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public Name(String first, String last) {
            super();
            this.first = first;
            this.last = last;
        }

        @Override
        public String toString() {
            return "Name [first=" + first + ", last=" + last + "]";
        }

        @Override
        public boolean equals(Object obj) {
            // if (this == obj)
            // return true;
            // if (obj == null)
            // return false;
            // if (getClass() != obj.getClass())
            // return false;
            // Name other = (Name) obj;
            // if (first == null) {
            // if (other.first != null)
            // return false;
            // } else if (!first.equals(other.first))
            // return false;
            // if (last == null) {
            // if (other.last != null)
            // return false;
            // } else if (!last.equals(other.last))
            // return false;
            // return true;
            if (this == obj) {
                return true;
            }
            if (obj.getClass() == Name.class) {
                Name n = (Name) obj;
                return n.first.equals(first);
            }
            return false;
        }

        // 开启hashCode可以保证2个初始化相同参数对象的equal函数返回true
        @Override
        public int hashCode() {
            // final int prime = 31;
            // int result = 1;
            // result = prime * result + ((first == null) ? 0 : first.hashCode());
            // result = prime * result + ((last == null) ? 0 : last.hashCode());
            // return result;

            return first.hashCode();
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Name n1 = new Name("ab", "c");
        Name n2 = new Name("ab", "c");
        Set<Name> s = new HashSet<>();
        s.add(n1);
        // contain先判断2个对象的hasCode是否相等，默认创建的2个对象引用是不一样的地址空间，需重写hashCode方法
        // 才能实现二者对象的相等
        System.out.println(s.contains(n2));

        Set<Name> set = new HashSet<>();
        n1 = new Name("ab", "c1");
        n2 = new Name("ab", "c2");

        System.out.println("n1 hasCode = " + n1.hashCode());
        System.out.println("n2 hasCode = " + n2.hashCode());
        set.add(n1);
        set.add(n2);
        set.forEach(n -> System.out.println(n.toString()));
    }

}
