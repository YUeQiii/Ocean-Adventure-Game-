package edu.uchicago.gerber._07streams;

import java.util.Currency;
import java.util.stream.Collectors;

public class E19_6 {

    public static void main(String[] args){
        Currency.getAvailableCurrencies().stream().map(Currency::getDisplayName).sorted().collect(Collectors.toList()).forEach(System.out::println);
    }
}
