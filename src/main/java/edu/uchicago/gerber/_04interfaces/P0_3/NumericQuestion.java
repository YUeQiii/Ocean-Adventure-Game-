package edu.uchicago.gerber._04interfaces.P0_3;

public class NumericQuestion extends Question {

    /**
     * Overirde the setAnswer method
     * Sets the answer for this question.
     *
     * @param correctResponse the answer
     */
    public void setAnswer(double correctResponse) {
        String correctRe = String.valueOf(correctResponse);
        super.setAnswer(correctRe);
    }

    /**
     * Override the checkAnswer method
     * Checks a given response for correctness.
     *
     * @param response the response to check
     * @return true if the response was correct, false otherwise
     */
    public boolean checkAnswer(double response) {
        String answer = getAnswer();
        double doubleAnswer = Double.valueOf(answer);
        if(Math.abs(doubleAnswer-response)<=0.01){
            return true;
        }
        else return false;
    }




}
