package edu.uchicago.gerber._06design.R12_10;
import java.util.*;
public class ChoiceQuestion extends Question{
    private List<String> choices;

    public ChoiceQuestion(String text, String answer) {
        super(text, answer);
        choices = new ArrayList<>();
    }

    public void addChoice(String choice, boolean correct) {
        choices.add(choice);
        if (correct) {
            // Set the choice number as the answer if this choice is correct
            String choiceNumber = "" + choices.size();
            super.setAnswer(choiceNumber);
        }
    }
    public void display() {
        // Display the question text
        super.display();
        // Display the answer choices
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ": " + choices.get(i));
        }
    }

}
