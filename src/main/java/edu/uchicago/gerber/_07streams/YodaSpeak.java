package edu.uchicago.gerber._07streams;

import java.util.Scanner;

public class YodaSpeak {


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence: ");
        String sentence = scanner.nextLine();

        String reversed = reverseSentence(sentence);

        System.out.println("Reversed sentence: " + reversed);


    }

    private static String reverseSentence(String sentence){
        String[] words = sentence.split(" ");
        StringBuilder reversed = new StringBuilder();

        for (int i = words.length-1; i>=0;i--){
            reversed.append(words[i]);
            if(i>0){
                reversed.append(" ");
            }
        }

        return reversed.toString();
    }

}
