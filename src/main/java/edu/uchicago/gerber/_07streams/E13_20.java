package edu.uchicago.gerber._07streams;

import java.util.ArrayList;
import java.util.*;

public class E13_20 {
    public static void main (String []args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an Integer price ");
        int number = scanner.nextInt();
        int[] candidate = new int[]{100, 20, 5,1};
        recursion(ans, new ArrayList<>(),candidate,number,0);
        System.out.println("There are" + ans.size()+"ways to pay for the price");
        for(List<Integer> l : ans){
            for(int i : l){
                System.out.print(String.valueOf(i) +" ");
            }
            System.out.println();
        }
    }
    public static List<List<Integer>> ans = new ArrayList<>();

    public static void recursion (List<List<Integer>> ans,  List<Integer>tmp, int[] candidate, int target,int index){
        if(target ==0){
            ans.add(new ArrayList<>(tmp));
        }
        if(target <0){
            return;
        }
        for(int i =index; i<candidate.length;i++){
            tmp.add(candidate[i]);
            recursion(ans,tmp,candidate,target-candidate[i],i);
            tmp.remove(tmp.size()-1);
        }
    }


}
