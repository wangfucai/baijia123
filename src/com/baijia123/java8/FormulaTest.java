package com.baijia123.java8;

public class FormulaTest {
    public static void main(String[] argv) {
        Formula formula = new Formula(){

            @Override
            public double calculate(int a) {
                // TODO Auto-generated method stub
                return sqrt(a * 100);
            }
            
        };
        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(16));
    }
}
