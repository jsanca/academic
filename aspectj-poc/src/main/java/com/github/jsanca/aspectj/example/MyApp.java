package com.github.jsanca.aspectj.example;

public class MyApp {

    @Cacheable(group = "mygroup", key = "mykey")
    @Transactional
    public void sayHello () {
        System.out.println("Hello World!");
    }

    @Transactional
    public static void sayHi() {
        System.out.println("Hi World!");
    }

    public static void testHi() {
        System.out.println("Hi Test World!");
    }

    public static void main( String[] args ) {
        sayHi();
        new MyApp().sayHello();
        testHi();
    }
}
