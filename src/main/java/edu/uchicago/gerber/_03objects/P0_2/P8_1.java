package edu.uchicago.gerber._03objects.P0_2;

public class P8_1 {
    public static void main(String[] args){
        Microwave microwave = new Microwave();
        microwave.increaseTime();
        microwave.increaseTime();
        microwave.togglePowerLevel();
        microwave.start();
        microwave.resetMicrowave();
        microwave.start();

    }

}

class Microwave{
    private int time;
    private int powerLevel;

    //Constructor
    public Microwave(){
        this.time = 0;
        this.powerLevel = 1;
    }

    public void increaseTime(){
        this.time += 30;
    }

    public void togglePowerLevel(){
        if(this.powerLevel == 1){
            this.powerLevel =2;
        }
        else{
            this.powerLevel =1;
        }
    }

    public void resetMicrowave(){
        this.time = 0;
        this.powerLevel = 1;
    }

    public void start(){
        if(this.time ==0){
            System.out.println("Please set the time.");
        }else{
            System.out.println("Cooking for " + this.time + " seconds at level " + this.powerLevel);
        }
    }


}
