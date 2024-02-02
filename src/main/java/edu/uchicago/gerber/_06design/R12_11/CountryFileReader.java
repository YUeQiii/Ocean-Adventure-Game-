package edu.uchicago.gerber._06design.R12_11;
import  java.util.*;
import java.io.*;

public class CountryFileReader {
    public List<Country> readCountriesFromFile(String filename) throws IOException {
        List<Country> countries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String name = parts[0].trim();
                    long population = Long.parseLong(parts[1].trim());
                    double area = Double.parseDouble(parts[2].trim());
                    countries.add(new Country(name, population, area));
                }
            }
        }
        return countries;
    }
}
