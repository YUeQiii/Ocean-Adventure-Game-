package edu.uchicago.gerber._03objects.P0_2;

public class P8_8 {
    public static void main(String[] args){
        VotingMachine machine = new VotingMachine();
        machine.voteDemocrat();
        machine.voteRepublican();
        machine.voteRepublican();
        machine.voteRepublican();
        machine.voteDemocrat();

        System.out.println("Democrat votes: " + machine.getDemocratTally());
        System.out.println("Republican votes: " + machine.getRepublicanTally());

        machine.clear();
        System.out.println("Democrat votes: " + machine.getDemocratTally());
        System.out.println("Republican votes: " + machine.getRepublicanTally());
    }
}

class VotingMachine{
    private int democratVotes;
    private int republicanVotes;


    public VotingMachine(){
        this.democratVotes = 0;
        this.republicanVotes = 0;
    }

    public void clear(){
        System.out.println("Machine cleared!");
        this.democratVotes = 0;
        this.republicanVotes = 0;
    }

    public void voteDemocrat(){
        democratVotes++;
    }

    public void voteRepublican(){
        republicanVotes++;
    }

    public int getRepublicanTally(){
        return republicanVotes;
    }

    public int getDemocratTally(){
        return democratVotes;
    }
}