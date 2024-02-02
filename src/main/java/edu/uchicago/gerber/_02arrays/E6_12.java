package edu.uchicago.gerber._02arrays;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class E6_12 {
    public static void main (String[] args) throws IOException {
        int[] array = generateRandomToss(20);
        for(int i : array){
            System.out.print(String.valueOf(i)+' ');
        }
        System.out.println();
        Arrays.sort(array);
        for(int i: array){
            System.out.print(String.valueOf(i)+' ');
        }
    }

    public static int[] generateRandomToss (int numberOfToss){
        int[] tossResults = new int[numberOfToss];
        Random random = new Random();

        for (int i =0; i< numberOfToss;i++){
            tossResults[i] =random.nextInt(100);
        }
        return tossResults;
    }
}
