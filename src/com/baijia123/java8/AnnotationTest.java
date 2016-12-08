package com.baijia123.java8;

public class AnnotationTest {
    public static void main(String[] argv) {
        Hint hint = AnnotationPerson1.class.getAnnotation(Hint.class);
        System.out.println(hint); // null

        Hints hints1 = AnnotationPerson1.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length); // 2

        Hint[] hints2 = AnnotationPerson1.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length); // 2
    }
}
