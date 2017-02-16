package com.baijia123.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class AESTest {
    
    public static String iv = "nubia123nubia456";
    
    public static String Encrypt(String sSrc, String sKey) throws Exception {  
        if (sKey == null) {  
            System.out.print("Key为空null");  
            return null;  
        }  
        // 判断Key是否为16位  
        if (sKey.length() != 16) {  
            System.out.print("Key长度不是16位");  
            return null;  
        }  
        byte[] raw = sKey.getBytes();  
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"  
        IvParameterSpec iv = new IvParameterSpec(AESTest.iv.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度  
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] srawt = sSrc.getBytes();
        byte[] encrypted = cipher.doFinal(srawt);  
        return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。  
    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String jsonStr = "{\"time_stamp\":1483668404,\"method\":\"cn.nubia.imei.imei.queryobtain\",\"querydata\":\"866925024123680\"}";
        System.out.println(AESTest.Encrypt(jsonStr, "abcdefghijklmnop"));
    }

}
