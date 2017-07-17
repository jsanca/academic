package com.github.jsanca;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * A test for synchronized
 */
public class ShareClass {

    private Map<String, String> map = null;

    public void initMe () {

        System.out.println("Get into initMe for thread: " + Thread.currentThread().getName() + ", date: " + new Date());

        if (null == map) {

            System.out.println("Map is null for thread: " + Thread.currentThread().getName());

            synchronized (ShareClass.class) {

                if (null == map) {
                    System.out.println("Doing init map for thread: " + Thread.currentThread().getName());
                    final HashMap tempmap = new HashMap<String, String>();
                    IntStream.range(1, 20).forEach((i) -> {
                        try {
                            Thread.sleep(1000);
                            System.out.println("--> Adding for thread: " + Thread.currentThread().getName() + ", date: " + new Date());
                            tempmap.put(i + "key", i + "value");
                        } catch (Exception e) {
                        }
                    });

                    this.map = new HashMap<>(tempmap);
                    System.out.println("Init map DONE for thread: " + Thread.currentThread().getName());
                }
            }
        }

        System.out.println("**> Printing values for thread: " + Thread.currentThread().getName() + ", date: " + new Date());
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : this.map.entrySet()) {

            stringBuilder.append(entry.getKey());
        }

        System.out.print(stringBuilder);

        System.out.println("\n\tGood bye for thread: " + Thread.currentThread().getName() + ", date: " + new Date());
    }

}
