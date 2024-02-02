package edu.uchicago.gerber._03objects.P0_2;

import com.sun.scenario.effect.light.SpotLight;

import java.util.ArrayList;

public class P8_14 {
    public static void main (String[] args){
        ArrayList<Country> countries = new ArrayList<>();

        countries.add(new Country("China", 5000000, 5000));
        countries.add(new Country("U.S.", 1000000, 2000));
        countries.add(new Country("Japan", 750000, 1500));

        Country largestAreaCoun = countries.get(0);
        Country largestPoCoun = countries.get(0);
        Country largestDenCoun = countries.get(0);

        for (Country country : countries) {
            if (country.getArea() > largestAreaCoun.getArea()) {
                largestAreaCoun = country;
            }
            if (country.getPopulation() > largestPoCoun.getPopulation()) {
                largestPoCoun = country;
            }
            if (country.getPopulationDensity() > largestDenCoun.getPopulationDensity()) {
                largestDenCoun = country;
            }
        }
        System.out.println("Country with the largest area: " + largestAreaCoun.getName());
        System.out.println("Country with the largest population: " + largestPoCoun.getName());
        System.out.println("Country with the largest population density: " + largestDenCoun.getName());


    }
}

class Country{
    private String name;
    private double population;
    private double area;

    public Country(String name, double population,double area){
        this.name = name;
        this.population = population;
        this.area = area;

    }

    public String getName(){
        return name;
    }

    public double getPopulation(){
        return population;
    }

    public double getArea(){
        return area;
    }

    //Calculate population density
    public double getPopulationDensity(){
        if(area ==0) return 0;
        return population/area;
    }


}
