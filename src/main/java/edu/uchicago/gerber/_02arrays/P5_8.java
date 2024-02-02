package edu.uchicago.gerber._02arrays;

import java.io.*;

public class P5_8 {
    public static void main (String[] args) throws IOException {
        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(re);
        while(true){
            System.out.print("Input the year(or type 'exit' to quit) :");
            st.nextToken();
            if(st.ttype == StreamTokenizer.TT_WORD){
                if("exit".equalsIgnoreCase(st.sval)){
                    System.out.println("Goodbye!");
                    break; // This breaks out of the while loop
                }
                else {
                    System.out.println("Wrong input value, please input again:");
                }
            }
            else if (st.ttype==StreamTokenizer.TT_NUMBER) {
                int Year = (int) st.nval;
                if (isLeapYear(Year)) {
                    System.out.println("Year " +Year + " is leap year.");
                } else {
                    System.out.println("Year " + Year + " is not leap year.");
                }

            }

        }
        
    }

    public static boolean isLeapYear(int Year){
        if (Year % 400 ==0){
            return true;
        }
        else if (Year % 100 ==0){
            return false;
        }
        else return Year % 4 == 0;

    }
}


