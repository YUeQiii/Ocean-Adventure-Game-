package edu.uchicago.gerber._02arrays;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P5_24 {
    public static void main (String[] args) throws IOException {

        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(re);
        while(true){
            System.out.print("Input the Roman number string (or type 'exit' to quit): ");
            st.nextToken();
            String romanNumberString = st.sval;
            if ("exit".equalsIgnoreCase(romanNumberString)) {
                System.out.println("Goodbye!");
                break; // This breaks out of the while loop
            }
            int total =0;
            List <Integer> list = new ArrayList<>();
            for (char c : romanNumberString.toCharArray()){
                list.add(getRomanNumericValue(c));
            }
            while(!list.isEmpty()){
                if(list.size()==1 || list.get(0)>=list.get(1)){
                    total += list.get(0);
                    list.remove(0);
                }
                else {
                    int diff = list.get(1)-list.get(0);
                    total += diff;
                    list.remove(1);
                    list.remove(0);
                }
            }
            System.out.println("The decimal number of Roman number "+String.valueOf(romanNumberString)+" is "+String.valueOf(total));
        }


    }
    public static int getRomanNumericValue(char romanChar) {
        switch (romanChar) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:
                throw new IllegalArgumentException(romanChar + " is not a valid Roman numeral.");
        }
    }
}
