package edu.uchicago.gerber._04interfaces.P0_3.E9_11;

public class Instructor extends Person {
    private double salary;

    public Instructor(String name, int yearOfBirth,double salary) {
        super(name, yearOfBirth);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString()+"[salary="+salary+"]";
    }
}
