package edu.uchicago.gerber._06design.E12_4;

import java.util.Scanner;

public class ArithmeticTeachingTool {
    private int score = 0;
    private int level = 1;
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the Arithmetic Teaching Tool!");

        while (level <= 3) {
            Level currentLevel = new Level(level);
            System.out.println("\nCurrent Level: " + level);
            while (score < 5) {
                boolean isCorrect = currentLevel.askQuestion(scanner);
                if (isCorrect) {
                    score++;
                    System.out.println("Correct! Your score is now: " + score);
                }
            }
            System.out.println("Congratulations, you've advanced to the next level!");
            level++;
            score = 0; // reset score for the next level
        }

        System.out.println("Well done! You have completed all levels.");
        scanner.close();
    }
}
