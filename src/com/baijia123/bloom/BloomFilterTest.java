package com.baijia123.bloom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.BitSet;

public class BloomFilterTest {
    // DEFAULT_SIZE为2的29次方，即此处的左移28位
    private static final int DEFAULT_SIZE = 2 << 28;
    /*
     * 不同哈希函数的种子，一般取质数 seeds数组共有8个值，则代表采用8种不同的哈希函数
     */
    private int[] seeds = new int[] { 3, 5, 7, 11, 13, 31, 37, 61 };
    /*
     * 初始化一个给定大小的位集 BitSet实际是由“二进制位”构成的一个Vector。 假如希望高效率地保存大量“开－关”信息，就应使用BitSet.
     */
    private BitSet bitSets = new BitSet(DEFAULT_SIZE);
    // 构建hash函数对象
    private SimpleHash[] hashFuns = new SimpleHash[seeds.length];
    // 布隆过滤器配置文件存放路径
    private String path = "";

    public BloomFilterTest(String path) {
        /**
         * 给出所有的hash值，共计seeds.length个hash值。共8位。 通过调用SimpleHash.hash(),可以得到根据8种hash函数计算得出hash值。
         * 传入DEFAULT_SIZE(最终字符串的长度），seeds[i](一个指定的质数)即可得到需要的那个hash值的位置。
         */
        for (int i = 0; i < seeds.length; i++) {
            hashFuns[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
        // 配置文件路径地址
        this.path = path;
    }

    public synchronized void add(String value) {
        for (SimpleHash hashFun : hashFuns) {
            bitSets.set(hashFun.hash(value), true);
        }
    }

    public synchronized boolean isExit(String value) {
        // 判断传入的值是否为null
        if (null == value) {
            return false;
        }

        for (SimpleHash hashFun : hashFuns) {
            if (!bitSets.get(hashFun.hash(value))) {
                // 如果判断8个hash函数值中有一个位置不存在即可判断为不存在Bloofilter中
                return false;
            }
        }

        return true;
    }

    public void init() {
        File file = new File(path);
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            long lt = System.currentTimeMillis();
            read(in);
            System.out.println(System.currentTimeMillis() - lt);
            System.out.println(Runtime.getRuntime().totalMemory());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void read(InputStream in) {
        if (null == in) { // 如果in为null，则返回
            return;
        }

        int i = 0;
        InputStreamReader reader = null;

        try {
            // 创建输入流
            reader = new InputStreamReader(in, "UTF-8");
            BufferedReader buffReader = new BufferedReader(reader, 512);
            String theWord = null;
            do {
                i++;
                theWord = buffReader.readLine();
                // 如果theWord不为null和空，则加入Bloomfilter中
                if (theWord != null && !theWord.trim().equals("")) {
                    add(theWord);
                }
                if (i % 10000 == 0) {
                    System.out.println(i);
                }

            } while (theWord != null);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            try {
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                // TODO: handle exception
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BloomFilterTest bloomFilterTest = new BloomFilterTest("f:/orders.php");
        bloomFilterTest.init();

        System.out.println(bloomFilterTest.isExit("http://www.plating.org/news_info.asp?pid=28&id=2857"));
    }
}
