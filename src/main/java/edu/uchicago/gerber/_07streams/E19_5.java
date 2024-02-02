package edu.uchicago.gerber._07streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E19_5 {
    public static <T> String toString(Stream<T> stream, int n) {
        // Limit the stream to the first n elements
        Stream<T> limitedStream = stream.limit(n);

        // Convert the stream to a comma-separated string
        return limitedStream.map(Object::toString)
                .collect(Collectors.joining(", "));
    }

    public static void main (String []args){
        Scanner scanner = new Scanner(System.in);

        // Read a line of input and split it into an array of strings
        System.out.println("Enter numbers separated by spaces:");
        String[] input = scanner.nextLine().split("\\s+");

        // Convert the input to a stream of integers
        Stream<Integer> intStream = Arrays.stream(input)
                .map(Integer::parseInt);

        // Read the number of elements to display
        System.out.println("Enter the number of elements to display:");
        int n = scanner.nextInt();

        // Display the first n elements as a comma-separated list
        System.out.println(toString(intStream, n));
    }



}
