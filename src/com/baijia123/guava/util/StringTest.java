package com.baijia123.guava.util;

import java.util.Arrays;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class StringTest {
    public static void main(String[] argv) {
        Joiner joiner = Joiner.on(";").skipNulls();
        System.out.println(joiner.join("Harry", null, "Ron", "Hermione"));
        System.out.println(Joiner.on(",").join(Arrays.asList(1, 5, 7)));

        System.out.println(Splitter.on(",").trimResults().omitEmptyStrings().split("foo,bar,,   qux"));

        String string = "xxx123123";
        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string);// �Ƴ�control�ַ�
        String theDigits = CharMatcher.DIGIT.retainFrom(string);// ֻ��������
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');// ȥ�����˵Ŀո񣬲����м�������ո��滻�ɵ����ո�
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*"); // ��*���滻��������
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string);// ֻ�������ֺ�Сд��ĸ
        
        byte[] bytes = string.getBytes(Charsets.UTF_8);
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME"));
    }
}
