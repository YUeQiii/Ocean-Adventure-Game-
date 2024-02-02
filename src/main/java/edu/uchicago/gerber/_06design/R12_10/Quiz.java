package edu.uchicago.gerber._06design.R12_10;
import java.util.*;

public class Quiz {

    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void display() {
        for (Question question : questions) {
            question.display();
            System.out.println();
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        for (Question question : questions) {
            question.display();
            System.out.print("Your answer: ");
            String response = scanner.nextLine();
            if (question.checkAnswer(response)) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is: " + question.getAnswer());
            }
            System.out.println();
        }
        scanner.close();
        System.out.println("You scored " + score + " out of " + questions.size());
    }

}


