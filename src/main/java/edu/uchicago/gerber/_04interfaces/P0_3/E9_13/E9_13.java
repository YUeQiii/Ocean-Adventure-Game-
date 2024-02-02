package edu.uchicago.gerber._04interfaces.P0_3.E9_13;

import java.awt.*;
import java.util.Scanner;

//The Rectangle class of the standard Java library does not supply a method to compute the area or the perimeter of a rectangle.
// Provide a subclass BetterRectangle of the Rectangle class that has getPerimeter and getArea methods.
// Do not add any instance variables. In the constructor, call the setLocation and setSize methods of the Rectangle class.
// Provide a program that tests the methods that you supplied.
public class E9_13 {
    public static void main(String[] args)
    {
        while(true){
            Scanner newScanx =  new Scanner(System.in);
            Scanner newScany =  new Scanner(System.in);
            Scanner newScanl =  new Scanner(System.in);
            System.out.println("Enter x or enter 'exit' to exit:");
            String x2 = newScanx.nextLine();
            if (x2.equals("exit")){
                break;
            }
            System.out.println("Enter y or enter 'exit' to exit:");
            String y2 = newScany.nextLine();
            if (y2.equals("exit")){
                break;
            }
            System.out.println("Enter length or enter 'exit' to exit:");
            String l2 = newScanl.nextLine();
            if (l2.equals("exit")){
                break;
            }

            int x = Integer.parseInt(x2);
            int y = Integer.parseInt(y2);
            int length = Integer.parseInt(l2);

            BetterRectangle betterRectangle = new BetterRectangle(x,y,length);
            System.out.println(betterRectangle.toString());

        }


    }

}

