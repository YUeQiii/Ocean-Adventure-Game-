package edu.uchicago.gerber._07streams;
import java.util.Scanner;


public class E13_4 {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int number = scanner.nextInt();
        System.out.println("Binary representation of " + number + " is: " + toBinary(number));

        scanner.close();

    }

    public static String toBinary(int n){
        if(n==0){
            return "";
        }
        int lastDigit = n%2;

        return (toBinary(n/2)+lastDigit);
    }
}
