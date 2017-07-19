package com.baijia123.java8.lambda;

public class LambdaScopeTest {

    int outerNum;

    static int outerStaticNum;

    void testScopes() {
        int num = 1;
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
        String convert = stringConverter.converter(2);
        System.out.println(convert);

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerNum = 13;
            System.out.println("inner outerNum = " + outerNum);
            return String.valueOf(from);
        };
        System.out.println(stringConverter2.converter(4));
        
        System.out.println(outerNum);
        outerNum = 5;
        String[] array = new String[1];
        Converter<Integer, String> stringConverter3 = (from) -> {
            array[0] = "Hi there";
            return String.valueOf(from);
        };
        stringConverter3.converter(23);
        System.out.println(array[0]);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new LambdaScopeTest().testScopes();
    }

}
