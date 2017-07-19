package com.baijia123.java8.lambda;

public class FormulaTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Formula formula = new Formula() {

            @Override
            public double calculate(int a) {
                // TODO Auto-generated method stub
                return sqrt(a * 100);
            }
            
        };
        
        System.out.println(formula.calculate(100));
        Formula formula2 = a -> a + 10;
        System.out.println(formula2.calculate(10));
    }

}
