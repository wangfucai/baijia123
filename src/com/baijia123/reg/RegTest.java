package com.baijia123.reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {
    
    private static final Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String value = "fixed,test";
        String[] names = NAME_SEPARATOR.split(value);
        System.out.println(names.length);
        
        Pattern p = Pattern.compile("(\\d{3,5})([a-z]{2})");
        String s = "123aa-34345bb-234cc-00";
        Matcher m = p.matcher(s);
        System.out.println("count = " + m.groupCount());//2组
        while(m.find()){
            System.out.println(m.group());//数字字母都有
            System.out.println(m.group(1));//只有数字
            System.out.println(m.group(2));//只有字母
        }
    }

}
