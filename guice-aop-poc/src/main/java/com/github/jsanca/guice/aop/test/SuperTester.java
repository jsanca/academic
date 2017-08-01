package com.github.jsanca.guice.aop.test;

public class SuperTester extends TestImpl implements Comparable<SuperTester> {

    @Override
    public int compareTo(SuperTester o) {
        return 0;
    }
}
