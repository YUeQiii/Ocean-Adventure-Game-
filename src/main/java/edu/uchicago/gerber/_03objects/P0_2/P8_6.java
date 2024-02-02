package edu.uchicago.gerber._03objects.P0_2;

public class P8_6 {
    public static void main(String[] args){
        Car myHybrid = new Car(50);
        myHybrid.addGas(20);
        myHybrid.drive(100);
        System.out.println(myHybrid.getGasLevel());

    }
}

class Car{
    double fuelEfficiency;
    double fuelInTank;

    public Car(double fuelEfficiency ){
        this.fuelEfficiency = fuelEfficiency;
        this.fuelInTank =0;
    }

    public void addGas(double fuelAmount){
        double total = this.fuelInTank+fuelAmount;
        this.fuelInTank = total;

    }

    public double getGasLevel(){
        return this.fuelInTank;
    }

    public void drive(double miles){
        double fuelConsumed = miles/fuelEfficiency;
        fuelInTank = fuelInTank-fuelConsumed;
    }

}