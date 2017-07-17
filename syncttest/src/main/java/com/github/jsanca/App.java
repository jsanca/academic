package com.github.jsanca;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        final ShareClass shareClass = new ShareClass();
        Thread [] threads = {
                new Thread(() -> shareClass.initMe()),
                new Thread(() -> shareClass.initMe()),
                new Thread(() -> shareClass.initMe()),
                new Thread(() -> shareClass.initMe()),
                new Thread(() -> shareClass.initMe()),
                new Thread(() -> shareClass.initMe()),
                new Thread(() -> shareClass.initMe()),
                new Thread(() -> shareClass.initMe()),
                new Thread(() -> shareClass.initMe()),
                new Thread(() -> shareClass.initMe()),
                new Thread(() -> shareClass.initMe()),
                new Thread(() -> shareClass.initMe()),
                new Thread(() -> shareClass.initMe()),
                new Thread(() -> shareClass.initMe())
        };

        for (Thread thread : threads) {

            thread.start();
        }
    }
}
