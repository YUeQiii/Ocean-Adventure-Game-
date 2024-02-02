package edu.uchicago.gerber._04interfaces.P0_3.E9_11;

public class Person {
    private String name;
    private int yearOfBirth;

    public Person(String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public String toString() {
        return getClass().getName()+"[name=" + name + ", year of birth="+yearOfBirth+"]";
    }

}
