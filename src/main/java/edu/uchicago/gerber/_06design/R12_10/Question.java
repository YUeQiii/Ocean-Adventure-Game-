package edu.uchicago.gerber._06design.R12_10;

public class Question {
    private String text;
    private String answer;

    public Question(String text, String answer){
        this.text= text;
        this.answer = answer;
    }

    public String getText(){
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public boolean checkAnswer(String response) {
        return response.equalsIgnoreCase(answer);
    }

    public void display() {
        System.out.println(text);
    }
}





