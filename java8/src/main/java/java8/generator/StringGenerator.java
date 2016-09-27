package java8.generator;


import java.security.SecureRandom;
import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class StringGenerator {

    private final Iterator<String> iterator;

    public StringGenerator(final Supplier<String> supplier) {

        final Stream<String> stream = Stream.iterate("", s -> supplier.get());

        this.iterator = stream.iterator();
    }

    public String next () {

        return this.iterator.next();
    }

    public static void main(final String [] args) {

        final StringGenerator generatorName = new StringGenerator(
                new RandomString(10)::random
        );

        final StringGenerator generatorDomain = new StringGenerator(
                new RandomString(6)::random
        );

        final StringGenerator generatorExt = new StringGenerator(
                new AlphabeticRandomString(3)::random
        );

        range(1, 10).forEach((i) -> System.out.println(i + " = " +
                generatorName.next() + "@" + generatorDomain.next() + '.' + generatorExt.next()));

    }

    public static class RandomString {

        private static final String ABC = "0123456789abcdefghijklmnopqrstuvwxyz";
        private final int stringLength;
        private final SecureRandom secureRandom;

        public RandomString (final int stringLength) {

            this.stringLength = stringLength;
            this.secureRandom = new SecureRandom();
        }

        public String random () {

            return this.random(this.stringLength);
        }

        public String random (final int stringLength) {

            return this.random(stringLength, ABC);
        }

        protected String random (final int stringLength, final String baseString) {

            final StringBuilder builder = new StringBuilder(stringLength);

            IntStream.range(0, stringLength)
                    .forEach(i -> builder.append(baseString.charAt(this.secureRandom.nextInt(baseString.length()))) );

            return builder.toString();
        }
    }

    public static class AlphabeticRandomString extends RandomString  {

        private static final String ABC = "abcdefghijklmnopqrstuvwxyz";

        public AlphabeticRandomString (final int stringLength) {

            super (stringLength);
        }

        @Override
        public String random (final int stringLength) {

            return this.random(stringLength, ABC);
        }
    }

}
