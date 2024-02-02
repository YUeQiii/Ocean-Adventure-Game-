package edu.uchicago.gerber._06design.E12_4;

import java.util.Scanner;
public class Question {

    private int number1;
    private int number2;
    private char operation;
    private int correctAnswer;

    public Question(int number1, int number2, char operation) {
        this.number1 = number1;
        this.number2 = number2;
        this.operation = operation;
        this.correctAnswer = operation == '+' ? number1 + number2 : number1 - number2;
    }

    public boolean ask(Scanner scanner) {
        for (int attempt = 0; attempt < 2; attempt++) {
            System.out.printf("Solve the following problem: %d %s %d = ?\n", number1, operation, number2);
            int playerAnswer = scanner.nextInt();
            if (playerAnswer == correctAnswer) {
                return true;
            } else {
                System.out.println("That's not quite right. Try again.");
            }
        }
        System.out.println("The correct answer was: " + correctAnswer);
        return false;
    }

}
