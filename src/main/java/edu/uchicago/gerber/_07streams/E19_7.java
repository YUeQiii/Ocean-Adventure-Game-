package edu.uchicago.gerber._07streams;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class E19_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter words separated by spaces:");
        String input = scanner.nextLine();

        // Split the input into words
        Stream<String> wordsStream = Arrays.stream(input.split("\\s+"));

        // Lambda expression for the transformation
        // It takes a string and returns a string formatted as "first letter...last letter"
        java.util.function.Function<String, String> abbreviate =
                str -> str.charAt(0) + "..." + str.charAt(str.length() - 1);

        // Apply the lambda expression to each word in the stream
        // Filter out words with fewer than two letters
        wordsStream.filter(word -> word.length() > 1)
                .map(abbreviate)
                .forEach(System.out::println);

    }
}
