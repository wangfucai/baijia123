package com.baijia123.java8.lambda;

public class LambdaConvertTest {

    public static class Something {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Converter<String, Integer> integerConverter1 = (from) -> Integer.valueOf(from);
        System.out.println("第1阶段：" + integerConverter1.converter("123"));

        // 方法引用
        Converter<String, Integer> integerConvert2 = Integer::valueOf;
        System.out.println("第2阶段1：" + integerConvert2.converter("222"));

        Something something = new Something();
        Converter<String, String> stringConverter = something::startsWith;
        System.out.println("第2阶段2：" + stringConverter.converter("Java"));

        // 构造方法引用
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person.getFirstName());
    }
}
