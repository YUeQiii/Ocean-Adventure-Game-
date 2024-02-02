package edu.uchicago.gerber._04interfaces.P0_3.E9_11;

public class Student extends Person {
    private String major;

    public Student(String name, int yearOfBirth,String major) {
        super(name, yearOfBirth);
        this.major = major;
    }

    @Override
    public String toString() {
        return super.toString()+"[major="+major+"]";
    }
}
