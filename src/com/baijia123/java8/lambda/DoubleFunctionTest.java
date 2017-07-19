package com.baijia123.java8.lambda;

import java.util.function.DoubleFunction;

public class DoubleFunctionTest {

    public static double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(DoubleFunctionTest.integrate(x -> x + 10, 3, 4));
    }

}
