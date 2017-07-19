package com.baijia123.java8.lambda;

public interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
