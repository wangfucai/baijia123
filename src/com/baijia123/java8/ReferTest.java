package com.baijia123.java8;

public class ReferTest {
    public static void main(String[] argv) {
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person.getFirstName() + " = " + person.getLastName());
        
        final int num = 1;
        
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
        System.out.println(stringConverter.convert(2));
    }
}
