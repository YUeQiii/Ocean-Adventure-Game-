package edu.uchicago.gerber._06design.R12_11;

public class Country {
    private String name;
    private long population;
    private double area;

    public Country(String name, long population, double area) {
        this.name = name;
        this.population = population;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public double getArea() {
        return area;
    }

    public double getPopulationDensity() {
        return population / area;
    }

    public String toString() {
        return name + " [Population: " + population + ", Area: " + area + " km²]";
    }

}

