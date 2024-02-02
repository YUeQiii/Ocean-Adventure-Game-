package edu.uchicago.gerber._02arrays;
import java.io.*;

public class E7_4 {

    public static  void main (String[] args){
        String inputFilename = "input.txt";
        String outputFilename = "output.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))){
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine())!= null){
                writer.write("/*" + lineNumber + " */" + line);
                writer.newLine();;
                lineNumber++;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }


    }
}
