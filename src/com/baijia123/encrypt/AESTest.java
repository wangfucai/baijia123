package com.baijia123.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class AESTest {
    
    public static String iv = "nubia123nubia456";
    
    public static String Encrypt(String sSrc, String sKey) throws Exception {  
        if (sKey == null) {  
            System.out.print("KeyΪ��null");  
            return null;  
        }  
        // �ж�Key�Ƿ�Ϊ16λ  
        if (sKey.length() != 16) {  
            System.out.print("Key���Ȳ���16λ");  
            return null;  
        }  
        byte[] raw = sKey.getBytes();  
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"�㷨/ģʽ/���뷽ʽ"  
        IvParameterSpec iv = new IvParameterSpec(AESTest.iv.getBytes());//ʹ��CBCģʽ����Ҫһ������iv�������Ӽ����㷨��ǿ��  
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] srawt = sSrc.getBytes();
        byte[] encrypted = cipher.doFinal(srawt);  
        return new BASE64Encoder().encode(encrypted);//�˴�ʹ��BASE64��ת�빦�ܣ�ͬʱ����2�μ��ܵ����á�  
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String jsonStr = "{\"time_stamp\":1483668404,\"method\":\"cn.nubia.imei.imei.queryobtain\",\"querydata\":\"866925024123680\"}";
        System.out.println(AESTest.Encrypt(jsonStr, "abcdefghijklmnop"));
    }

}
