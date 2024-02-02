package edu.uchicago.gerber._04interfaces.P0_3.E9_11;
//Implement a superclass Person. Make two classes, Student and Instructor, that inherit from Person. A person has a name and a year of birth. A student has a major, and
//an instructor has a salary. Write the class declarations, the constructors, and the methods toString for all classes. Supply a test program that tests these classes and methods.

public class E9_11 {
    public static void main(String[] args) {
        Student student = new Student("Jenny", 2001, "Computer Science");
        Instructor instructor = new Instructor("Lisa", 1980, 60000);

        System.out.println(student);
        System.out.println(instructor);
    }
}

