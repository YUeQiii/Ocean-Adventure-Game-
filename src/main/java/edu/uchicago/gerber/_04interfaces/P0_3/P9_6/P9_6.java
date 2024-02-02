package edu.uchicago.gerber._04interfaces.P0_3.P9_6;
import java.util.Scanner;
//Implement a superclass Appointment and subclasses Onetime, Daily, and Monthly.
// An appointment has a description (for example, “see the dentist”) and a date. Write a method occursOn(int year, int month, int day) that checks whether the appointment occurs on that date.
// For example, for a monthly appointment, you must check whether the day of the month matches. Then fill an array of Appointment objects with a mixture of appointments.
// Have the user enter a date and print out all appointments that occur on that date.

public class P9_6 {
    public static void main(String[] args) {
        Appointment[] appointments = {
                new Onetime("See the dentist", 2023, 10, 20),
                new Onetime("See the doctor", 2023, 7, 26),
                new Onetime("See the dentist", 2023, 5, 13),
                new Daily("Morning exercise", 2023, 1, 1),
                new Daily("Evening exercise", 2023, 12, 25),
                new Monthly("Pay rent", 2023, 8, 16),
                new Monthly("Pay tuition", 2023, 4, 23),
                new Monthly("Buy clothes", 2023, 3, 25),
                new Monthly("Buy books", 2023, 1, 7)
        };
        Scanner scanner = new Scanner(System.in);
        while (true){

            System.out.print("Enter a date (yyyy mm dd) or enter '0' to exit: ");
            int year = scanner.nextInt();
            if(year ==0){
                break;
            }
            int month = scanner.nextInt();
            int day = scanner.nextInt();

            System.out.println("Appointments on " + year + "-" + month + "-" + day + ":");
            for (Appointment app : appointments) {
                if (app.occursOn(year, month, day)) System.out.println(app.getDescription());
            }
        }

    }


}
