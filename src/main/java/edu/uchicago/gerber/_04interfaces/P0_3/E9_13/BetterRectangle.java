package edu.uchicago.gerber._04interfaces.P0_3.E9_13;

import java.awt.*;

public class BetterRectangle extends Rectangle {
    //private double perimeter;
    //private double area;

    public BetterRectangle(int x, int y, int length) {
        setLocation(x - length / 2, y - length / 2);
        setSize(length, length);
    }

    public int getArea() {
        return (int) (getWidth() * getHeight());
    }

    public int getPerimeter() {
        return (int) (2 * getWidth() + 2 * getHeight());
    }

    public String toString() {
        int x = (int) getX();
        int y = (int) getY();
        int w = (int) getWidth();
        int h = (int) getHeight();
        return"Square[x=" + x +",y=" + y +",width=" + w +",height=" + h +"]";
    }
}
