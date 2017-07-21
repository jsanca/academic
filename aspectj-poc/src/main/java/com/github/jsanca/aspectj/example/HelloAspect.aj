package com.github.jsanca.aspectj.example;

public aspect HelloAspect {
    pointcut callSayMessage() : call(public static void com.github.jsanca.aspectj.example.MyApp.test*(..));
    before() : callSayMessage() {
        System.out.println("*********Good day!");
    }
    after() : callSayMessage() {
        System.out.println("*********Thank you!");
    }
}
