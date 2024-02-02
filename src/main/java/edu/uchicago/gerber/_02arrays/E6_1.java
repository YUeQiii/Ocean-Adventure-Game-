package edu.uchicago.gerber._02arrays;

import java.io.*;
import java.util.*;

public class E6_1 {
    public static void main (String[] args) throws IOException {
        int[] array = generateRandomToss(10);
        for(int i =0; i<array.length;i++){
            if(i%2 ==0){
                System.out.print(String.valueOf(array[i])+' ');
            }
        }
        System.out.println();
        for(int i =0; i<array.length;i++){
            if(array[i]%2 == 0){
                System.out.print(String.valueOf(array[i])+' ');
            }
        }
        System.out.println();
        for (int i = array.length-1;i>=0;i--){
            System.out.print(String.valueOf(array[i])+' ');
        }
        System.out.println();
        System.out.print(String.valueOf(array[0])+' ');
        System.out.print(array[array.length-1]);
    }

    public static int[] generateRandomToss (int numberOfToss){
        int[] tossResults = new int[numberOfToss];
        Random random = new Random();

        for (int i =0; i< numberOfToss;i++){
            tossResults[i] = random.nextInt();
        }
        return tossResults;
    }
}
