package edu.uchicago.gerber._06design.R12_11;
import  java.util.*;

public class CountryStatistics {

    private List<Country> countries;

    public CountryStatistics(List<Country> countries){
        this.countries = countries;
    }

    public Country getCountryByLargestArea() {
        return countries.stream()
                .max((c1, c2) -> Double.compare(c1.getArea(), c2.getArea()))
                .orElse(null);
    }

    public Country getCountryByLargestPopulation() {
        return countries.stream()
                .max((c1, c2) -> Long.compare(c1.getPopulation(), c2.getPopulation()))
                .orElse(null);
    }

    public Country getCountryByLargestPopulationDensity() {
        return countries.stream()
                .max((c1, c2) -> Double.compare(c1.getPopulationDensity(), c2.getPopulationDensity()))
                .orElse(null);
    }


}
