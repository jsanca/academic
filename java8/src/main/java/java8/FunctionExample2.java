package java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FunctionExample2 {

    private static final String WORD_REGEXP = " ";

    public static void main(String[] args) {

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("SomeFile.txt"), StandardCharsets.UTF_8)) {
            reader.lines()
                    .flatMap(line -> Stream.of(line.split(WORD_REGEXP))) // split the lines by spaces
                    .distinct() // remove the repeat word
                    .map(String::toLowerCase) // lowerCase
                    .forEach(System.out::println); // print there
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
