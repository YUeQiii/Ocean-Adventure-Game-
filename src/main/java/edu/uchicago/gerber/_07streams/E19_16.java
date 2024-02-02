package edu.uchicago.gerber._07streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

//What happens when you run the program multiple times?
/*
When you run this program multiple times, you may get different results each time. This is due to the nature of parallel
streams and the findAny method:
(1) Parallel Streams: The parallel stream processes elements in parallel, potentially in a different order each time
the program runs. This is because the stream's elements are divided into multiple segments, each processed independently and concurrently.
(2) findAny: The findAny method returns any element that matches the criteria, without guaranteeing which one will be
returned. In a parallel stream, this means you might get a different element from the available matches each time you
run the program, depending on which part of the stream finishes processing first.
*/

public class E19_16 {
    public static void main(String[] args){
        String filePath = "/usr/share/dict/words";
        try {
            // Read all lines from the file into an ArrayList
            ArrayList<String> words = new ArrayList<>(Files.readAllLines(Paths.get(filePath)));

            // Find any palindrome with at least five letters using a parallel stream
            Optional<String> palindrome = words.parallelStream()
                    .filter(word -> word.length() >= 5) // Check length
                    .filter(E19_16::isPalindrome) // Check if it's a palindrome
                    .findAny(); // Find any palindrome that matches the criteria

            // Print the found palindrome
            palindrome.ifPresent(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static boolean isPalindrome(String word) {
        return new StringBuilder(word).reverse().toString().equalsIgnoreCase(word);
    }

}
