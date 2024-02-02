package edu.uchicago.gerber._03objects.P0_2;
import java.util.*;
public class P8_19 {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the starting angle (in degrees): ");
        double angle = scanner.nextDouble();
        System.out.print("Enter the initial velocity (m/s): ");
        double velocity = scanner.nextDouble();
        Cannonball cannonball = new Cannonball(10);
        cannonball.shoot(angle, velocity);

    }
}
class Cannonball{
    private double xPosition;
    private double yPosition;
    private double xVelocity;
    private double yVelocity;

    private static final double GrAVITY = -9.81;

    public Cannonball(double xPositon){
        this.xPosition = xPositon;
        this.yPosition = 0;
        this.xVelocity =0;
        this.yVelocity =0;

    }
    public void move(double sec){
        xPosition += xVelocity*sec;
        yPosition += yVelocity*sec;

        yVelocity += GrAVITY*sec;

    }

    public double getX(){
        return xPosition;
    }

    public double getY(){
        return yPosition;
    }

    public void shoot (double angle, double initialVelocity){
        //Convert the angle to radians
        double radians = Math.toRadians(angle);

        //Compute the x and y velocities
        xVelocity = initialVelocity*Math.cos(radians);
        yVelocity = initialVelocity*Math.sin(radians);

        while(yPosition >= 0){
            move(0.1);
            System.out.println("X: " + getX() + ", Y: " + getY());
        }

    }
}