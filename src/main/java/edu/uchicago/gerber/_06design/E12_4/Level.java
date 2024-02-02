package edu.uchicago.gerber._06design.E12_4;
import java.util.Random;
import java.util.Scanner;

public class Level {
    private int levelNumber;
    private Random random = new Random();

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public boolean askQuestion(Scanner scanner) {
        Question question = generateQuestion();
        return question.ask(scanner);
    }

    private Question generateQuestion() {
        int number1 = getRandomNumber();
        int number2 = getRandomNumber(levelNumber == 3);

        switch (levelNumber) {
            case 1:
                while (number1 + number2 >= 10) {
                    number1 = getRandomNumber();
                    number2 = getRandomNumber();
                }
                return new Question(number1, number2, '+');
            case 2:
                return new Question(number1, number2, '+');
            case 3:
                while (number1 < number2) {
                    number1 = getRandomNumber();
                    number2 = getRandomNumber(levelNumber == 3);
                }
                return new Question(number1, number2, '-');
            default:
                throw new IllegalStateException("Unexpected level: " + levelNumber);
        }
    }
    private int getRandomNumber() {
        return getRandomNumber(false);
    }

    private int getRandomNumber(boolean nonNegative) {
        return nonNegative ? random.nextInt(9) + 1 : random.nextInt(10);
    }
}
