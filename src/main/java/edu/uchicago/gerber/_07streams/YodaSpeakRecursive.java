package edu.uchicago.gerber._07streams;

import java.util.Scanner;

public class YodaSpeakRecursive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a sentence: ");
        String sentence = scanner.nextLine();

        String reversed = reverseWords(sentence.split(" "), 0);

        System.out.println("Reversed sentence: " + reversed);
    }

    private static String reverseWords(String[] words, int index) {
        if (index >= words.length) {
            return "";
        }

        // Call the method recursively for the next word
        String reversed = reverseWords(words, index + 1);

        // Add the current word
        if (!reversed.isEmpty()) {
            reversed += " ";
        }
        reversed += words[index];

        return reversed;
    }
}


