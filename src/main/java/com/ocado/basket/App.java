package com.ocado.basket;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class App {
    static String pathToBasket = "C:/Java/Java Stack/basket-splitter/src/test/resources/basket-1.json";

    public static void main( String[] args ) {
        List<String> basket = basketInit();
        System.out.println("Initial basket: " + basket);
        BasketSplitter basketSplitter = new BasketSplitter(
                "C:/Java/Java Stack/basket-splitter/src/main/resources/config.json");
        System.out.println("Result of splitting: " + basketSplitter.split(basket));
    }

    private static List<String> basketInit() {
        List<String> mapOfProducts = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            mapOfProducts = objectMapper.readValue(new File(pathToBasket), List.class);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mapOfProducts;
    }

}
