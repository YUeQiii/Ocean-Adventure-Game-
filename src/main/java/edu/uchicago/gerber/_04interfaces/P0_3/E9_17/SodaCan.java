package edu.uchicago.gerber._04interfaces.P0_3.E9_17;


public class SodaCan implements Measurable {

    private final double height;
    private final double radius;
    private static final double PI = Math.PI;

    // Constructor
    public SodaCan(double height, double radius) {
        this.height = height;
        this.radius = radius;
    }

    // Method to calculate the surface area
    public double getSurfaceArea() {
        double circleArea = 2 * PI * Math.pow(radius, 2);
        double sideArea = 2 * PI * radius * height;
        return circleArea + sideArea;
    }

    @Override
    public double measure() {
        return getSurfaceArea();
    }

    // Method to calculate the volume
    public double getVolume() {
        return PI * Math.pow(radius, 2) * height;
    }


}
