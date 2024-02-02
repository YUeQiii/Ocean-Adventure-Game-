package edu.uchicago.gerber._07streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E19_14 {
    public static void main(String[] args) {
        String filePath = "/usr/share/dict/words"; // Replace with your file path

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            Map<Character, Double> averageLengths = lines
                    .flatMap(line -> Arrays.stream(line.split("\\s+"))) // Split lines into words
                    .filter(word -> !word.isEmpty()) // Filter out empty strings
                    .collect(Collectors.groupingBy(
                            word -> Character.toLowerCase(word.charAt(0)), // Group by the first letter
                            Collectors.averagingInt(String::length) // Calculate average length
                    ));

            averageLengths.forEach((letter, avgLength) ->
                    System.out.println(letter + ": " + avgLength));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

