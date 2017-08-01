package com.github.jsanca.guice.aop;

import java.io.Serializable;

public class PrintHelperImpl implements PrintHelper, Serializable {
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
}
