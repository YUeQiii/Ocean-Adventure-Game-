package edu.uchicago.gerber._04interfaces.P0_3.P9_1;

//Implement a class Clock whose getHours and getMinutes methods return the current time at your location. (Call java.time.LocalTime.now().toString() or, if you are not using Java 8, new java.util.Date().toString() and extract the time from that string.)
// Also provide a getTime method that returns a string with the hours and minutes by calling the getHours and getMinutes methods.
// Provide a subclass WorldClock whose constructor accepts a time offset. For example, if you live in California, a new WorldClock(3) should show the time in New York, three time zones ahead.
// Which methods did you override? (You should not override getTime.)

public class P9_1 {
        public static void main(String[] args) {
            Clock localClock = new Clock();
            WorldClock worldClock = new WorldClock(3); // 3-hour offset

            System.out.println("Local Time: " + localClock.getTime());
            System.out.println("World Clock: " + worldClock.getTime());
        }
}



