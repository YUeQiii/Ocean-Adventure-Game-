package edu.uchicago.gerber._06design.R12_14;

import java.util.List;

public class Employee {
    private String name;
    private double hourlyRate;
    private double hoursWorked;

    public Employee(String name, double hourlyRate){
        this.name=name;
        this.hourlyRate = hourlyRate;
        this.hoursWorked =0;
    }


    public String getName(){
        return this.name;
    }

    public double getHourlyRate(){
        return  this.hourlyRate;
    }

    public double getHoursWorked(){
        return this.hoursWorked;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    public void setHoursWorked(double hoursWorked){
        this.hoursWorked = hoursWorked;
    }

    public void reportHours(double hours){
        this.hoursWorked += hours;
    }

    public Paycheck generatePaycheck(){
        PayrollCalculator calculator = new PayrollCalculator();
        double payAmount = calculator.calculatePay(this);
        return new Paycheck(payAmount);
    }

}
