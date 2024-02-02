package edu.uchicago.gerber._02arrays;

import java.io.*;
import  java.util.*;

public class E6_16 {
    public static void main (String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int[] values = new int[str.length];
        int i =0;
        for (String s : str){
            values[i] = Integer.parseInt(s);
            i++;
        }
        int max = Integer.MIN_VALUE;
        for (int v: values){
            if(v > max){
                max = v;
            }
        }
        int[] stars = new int[values.length];
        for (int j =0; j< stars.length;j++){
            stars[j] = (int) (values[j] * 20.0 / max);
            //System.out.println(stars[j]);
        }
        Character[][] chart = new Character[stars.length][20];
        for(int j =0; j<stars.length;j++){
            for (int k =0; k<20;k++) {
                if (k < stars[j]) {
                    chart[j][k] = '*';
                }
                else {
                    chart[j][k] = ' ';
                }
            }
        }
        for(int j = 0; j<20; j++){
            for (int k =0; k< stars.length;k++){
                System.out.print(chart[k][19-j]);
            }
            System.out.println();
        }


    }

}
