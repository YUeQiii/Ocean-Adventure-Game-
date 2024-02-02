package edu.uchicago.gerber._03objects.P0_2;

public class P8_5 {
    public static void main(String[] args){
        SodaCan sodacan = new SodaCan(3,3);
        System.out.println("The SurfaceArea is" + String.valueOf(sodacan.getSurfaceArea()));
        System.out.println("The Volume is" + String.valueOf(sodacan.getVolume()));
    }

}

class SodaCan{
    private int height;
    private int radius;

    public SodaCan(int height, int radius){
        this.height = height;
        this.radius = radius;
    }

    public double getSurfaceArea(){
        double surfaceArea = 2*Math.PI*radius*height+(2*Math.PI*Math.pow(radius,2));
        return surfaceArea;
    }

    public double getVolume(){
        double volume = Math.PI*Math.pow(radius,2)*height;
        return volume;

    }

}