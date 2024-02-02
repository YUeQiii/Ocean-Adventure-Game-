package edu.uchicago.gerber._02arrays;

import java.io.*;
import java.util.*;

class CSVReader{
    private List<List<String>> data = new ArrayList<>();
    //Contributor of CVS file
    public CSVReader(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(parseCSVLine(line));
            }
        }
    }
    private List<String> parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        boolean insideQuotes = false;
        StringBuilder field = new StringBuilder();

        for (char ch : line.toCharArray()) {
            if (insideQuotes) {
                if (ch == '"') {
                    insideQuotes = false;
                }
                else {
                    field.append(ch);
                }
            } else {
                if (ch == '"') {
                    insideQuotes = true;
                } else if (ch == ',' ) {
                    fields.add(field.toString());
                    field.setLength(0); //clear the StringBuilder
                } else {
                    field.append(ch);
                }
            }
        }
        fields.add(field.toString());
        return fields;
    }

    public int numberOfRows() {
        return data.size();
    }

    public int numberOfFields(int row) {
        return data.get(row).size();
    }

    public String field(int row, int column) {
        return data.get(row).get(column);
    }

}
public class P7_5 {
    public static void main (String[] args) throws IOException {
        CSVReader reader = new CSVReader("data.csv");
        System.out.println("Rows: " + reader.numberOfRows());
        System.out.println("Fields in Row 0: " + reader.numberOfFields(0));
        System.out.println("Field [0][2]: " + reader.field(0, 2));
    }

}
