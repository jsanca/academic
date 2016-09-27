package java8;

import java.util.function.Function;

public class FunctionExample {


    public static void main(String[] args) {

        Function<Integer,Integer> add1 = x -> x + 1;
        Function<String,String> concat = x -> x + 1;

        int result = add1.apply(3);

        System.out.println(result);

        String sResult = concat.apply("hello");

        System.out.println(sResult);

        // method reference
        Function<Integer,Integer> add2 = Utils::add1;
        Function<String,String> concat2 = Utils::concat1;

        result = add2.apply(3);

        System.out.println(result);

        sResult = concat2.apply("hello");

        System.out.println(sResult);
    }
}


class Utils {
    public static Integer add1(Integer x) { return x + 2; }
    public static String concat1(String x) { return x + 2; }
}
