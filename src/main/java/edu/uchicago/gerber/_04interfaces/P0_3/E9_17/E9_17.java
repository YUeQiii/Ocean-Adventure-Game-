package edu.uchicago.gerber._04interfaces.P0_3.E9_17;
//Modify the SodaCan class of Exercise P8.5 to implement the Measurable interface. The measure of a soda can should be
// its surface area. Write a program that computes the average surface area of an array of soda cans.
public class E9_17 {
    public static void main(String[] args) {
        SodaCan can = new SodaCan(10, 2.5);
        System.out.printf("Surface Area: %.2f%n", can.getSurfaceArea());
        System.out.printf("Volume: %.2f%n", can.getVolume());

        SodaCan[] cans = {
                new SodaCan(10, 2.5),
                new SodaCan(12, 3),
                new SodaCan(8, 2)
        };

        System.out.printf("Average Surface Area: %.2f%n", average(cans));

    }
    public static double average(Measurable[] objects) {
        if (objects.length == 0) return 0;
        double sum = 0;
        for (Measurable obj : objects) {
            sum += obj.measure();
        }
        return sum / objects.length;
    }

}

