package java8.generator;


import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.generate;
import static java.util.stream.IntStream.range;

public class Generator {

    private final Iterator<Integer> iterator;

    public Generator (final IntSupplier supplier) {

        final IntStream stream = generate
                (supplier);

        this.iterator = stream.iterator();
    }

    public Integer next () {

        return this.iterator.next();
    }

    public static void main(final String [] args) {

        final Generator generator = new Generator(
                () -> (int) (Math.random() * 100)
        );

        range(1, 10).forEach((i) -> System.out.println(i + " = " + generator.next()));

        System.out.println("Sequencial");
        // thread-safe sequencial incremental
        final Generator generatorSequencial = new Generator(
                new AtomicInteger()::getAndIncrement
        );

        range(1, 10).forEach((i) -> System.out.println(i + " = " + generatorSequencial.next()));

    }

}
