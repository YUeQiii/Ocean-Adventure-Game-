package edu.uchicago.gerber._07streams;

import java.util.*;
import java.io.*;
import java.nio.file.*;

public class P13_3 {

    private static final String[] KEYPAD = {
            "", "abc","def"," ghi","jkl","mno","pqrs","tuv","wxyz"
    };

    private static final Set<String> validWords = new HashSet<>();

    public static void main(String[] args) {
        try{
            readValidWords("/usr/share/dict/words");
            //input
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a number");
            String phoneNumber = scanner.nextLine().replaceAll("\\D+", "");
            List<String> wordSequences = findWordSequences(phoneNumber);
            for (String sequence : wordSequences) {
                if(sequence.equals("code in java")){
                    System.out.println(sequence);
                }
                //System.out.println(sequence);
            } scanner.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void readValidWords(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                validWords.add(line.trim().toLowerCase());
            }
        }
    }

    public static List<String> phoneNumberToWords(String segment){
        List<String> results = new ArrayList<>();
        if(segment == null || segment.length()==0){
            return  results;
        }
        findCombinations(segment, 0, "", results);
        return results;
    }

    private static void findCombinations(String segment, int index, String current, List<String> results){
        if (index == segment.length()) {
            if (isValidWordSequence(current)) {
                results.add(current);
            }
            return;
        }

        String possibleLetters = KEYPAD[segment.charAt(index) - '0'-1];
        for (char letter : possibleLetters.toCharArray()) {
            findCombinations(segment, index + 1, current + letter, results);
        }
    }

    private static List<String> findWordSequences(String phoneNumber) {
        List<String> results = new ArrayList<>();
        findWordSequences(phoneNumber, 0, new ArrayList<>(), results);
        return results;
    }

    private static void findWordSequences(String phoneNumber, int start, List<String> current, List<String> results) {
        if (start == phoneNumber.length()) {
            results.add(String.join(" ", current));
            return;
        }

        for(int i = start+1; i<= phoneNumber.length();i++){
            String segment = phoneNumber.substring(start, i);
            List<String> words = phoneNumberToWords(segment);
            for (String word : words) {
                current.add(word);
                findWordSequences(phoneNumber, i, current, results);
                current.remove(current.size() - 1); // backtrack
            }
        }

    }
    private static boolean isValidWordSequence(String wordSequence) {
        return validWords.contains(wordSequence.toLowerCase());
    }

}
