package com.baijia123.reflect;

public class TestClass implements TestInterface {

    @Override
    public void f() {
        // TODO Auto-generated method stub
        System.out.println("public C.f()");
    }
    
    public void g() {
        System.out.println("public C.g()");
    }
    
    protected void v() {
        System.out.println("protected C.v()");
    }
    
    void u() {
        System.out.println("package C.u()");
    }
    
    @SuppressWarnings("unused")
    private void w() {
        System.out.println("private C.w()");
    }
    
}
