package edu.uchicago.gerber._02arrays;

import java.io.*;
import java.util.*;

public class E6_9 {
    public static void main (String[] args) {
        System.out.println("Input the integer element for array a.");
        Scanner sc = new Scanner(System.in);
        String[] str_a = sc.nextLine().split(" ");
        System.out.println("Input the integer element for array b");
        sc = new Scanner(System.in);
        String[] str_b = sc.nextLine().split(" ");
        int [] a = new int[str_a.length];
        int [] b = new int[str_b.length];
        int i =0, j=0;
        for (String s : str_a){
            a[i]= Integer.parseInt(s);
            i++;
        }
        for (String s : str_b){
            b[j]= Integer.parseInt(s);
            j++;
        }
        if(equals(a, b)){
            System.out.println("Two arrays are the same.");
        }
        else {
            System.out.println("Two arrays are different.");
        }


    }
    public static boolean equals(int[] a, int[] b){
        if (a.length != b.length){
            return false;
        }
        for (int i =0; i<a.length;i++){
            if (a[i]!=b[i]){
                return false;
            }
        }
        return true;
    }

}
