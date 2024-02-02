package edu.uchicago.gerber._03objects.P0_2;

public class P8_7 {
    public static void main(String[] args) {
        System.out.println("Input three integers between 0 to 39 to set the lock.");
        ComboLock lock = new ComboLock(5, 10, 15);
        lock.reset();
        lock.turnRight(35);  // Turn right to 5
        lock.turnLeft(5);   // Turn left to 10
        lock.turnRight(25); // Turn right to 15
        if (lock.open()) {
            System.out.println("Lock opened!");
        } else {
            System.out.println("Failed to open the lock.");
        }
    }

}


class ComboLock {
    private  int secret1, secret2, secret3;
    private int currentPosition;
    private  int [] attemps = new int[3];//Store user's input
    private int attempIndex = 0;

    public ComboLock(int secret1, int secret2, int secret3){
        this.secret1 = secret1;
        this.secret2 = secret2;
        this.secret3 = secret3;
        this.currentPosition = 0;
    }

    public void reset(){
        currentPosition = 0;
        attempIndex =0;
    }

    public void turnLeft(int ticks){
        currentPosition =(currentPosition + ticks)%40;
        if(attempIndex<3){
            attemps[attempIndex++] = currentPosition;
        }

    }

    public void turnRight (int ticks){
        currentPosition = (currentPosition - ticks + 40)%40;
        if(attempIndex<3){
            attemps[attempIndex++] = currentPosition;
        }
    }

    public boolean open(){
        return attemps[0]==secret1 && attemps[1]==secret2 && attemps[2]==secret3;

    }
}
