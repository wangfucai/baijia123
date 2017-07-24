package com.baijia123.bloom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.BitSet;

public class BloomFilterTest {
    // DEFAULT_SIZEΪ2��29�η������˴�������28λ
    private static final int DEFAULT_SIZE = 2 << 28;
    /*
     * ��ͬ��ϣ���������ӣ�һ��ȡ���� seeds���鹲��8��ֵ����������8�ֲ�ͬ�Ĺ�ϣ����
     */
    private int[] seeds = new int[] { 3, 5, 7, 11, 13, 31, 37, 61 };
    /*
     * ��ʼ��һ��������С��λ�� BitSetʵ�����ɡ�������λ�����ɵ�һ��Vector�� ����ϣ����Ч�ʵر�������������ء���Ϣ����Ӧʹ��BitSet.
     */
    private BitSet bitSets = new BitSet(DEFAULT_SIZE);
    // ����hash��������
    private SimpleHash[] hashFuns = new SimpleHash[seeds.length];
    // ��¡�����������ļ����·��
    private String path = "";

    public BloomFilterTest(String path) {
        /**
         * �������е�hashֵ������seeds.length��hashֵ����8λ�� ͨ������SimpleHash.hash(),���Եõ�����8��hash��������ó�hashֵ��
         * ����DEFAULT_SIZE(�����ַ����ĳ��ȣ���seeds[i](һ��ָ��������)���ɵõ���Ҫ���Ǹ�hashֵ��λ�á�
         */
        for (int i = 0; i < seeds.length; i++) {
            hashFuns[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
        // �����ļ�·����ַ
        this.path = path;
    }

    public synchronized void add(String value) {
        for (SimpleHash hashFun : hashFuns) {
            bitSets.set(hashFun.hash(value), true);
        }
    }

    public synchronized boolean isExit(String value) {
        // �жϴ����ֵ�Ƿ�Ϊnull
        if (null == value) {
            return false;
        }

        for (SimpleHash hashFun : hashFuns) {
            if (!bitSets.get(hashFun.hash(value))) {
                // ����ж�8��hash����ֵ����һ��λ�ò����ڼ����ж�Ϊ������Bloofilter��
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
        if (null == in) { // ���inΪnull���򷵻�
            return;
        }

        int i = 0;
        InputStreamReader reader = null;

        try {
            // ����������
            reader = new InputStreamReader(in, "UTF-8");
            BufferedReader buffReader = new BufferedReader(reader, 512);
            String theWord = null;
            do {
                i++;
                theWord = buffReader.readLine();
                // ���theWord��Ϊnull�Ϳգ������Bloomfilter��
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
            // �ر���
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
