package com.ocado.basket.domain;

import java.util.List;

public class Product {
    private final String name;
    private final List<String> deliveryMethods;
    public Product(String name, List<String> deliveryOptions){
        this.name = name;
        this.deliveryMethods = deliveryOptions;
    }
    public String name(){
        return this.name;
    }
    public List<String> getDeliveryMethods(){
        return this.deliveryMethods;
    }

    @Override
    public String toString() {
        return "Product name: " + name + ", delivery methods: " + deliveryMethods;
    }
}
