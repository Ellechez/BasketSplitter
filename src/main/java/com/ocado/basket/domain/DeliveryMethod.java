package com.ocado.basket.domain;

import java.util.List;

public class DeliveryMethod {
    private final String name;
    private final List<String> products;

    public DeliveryMethod(String name, List<String> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public List<String> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Delivery method: " + name + ", products: " + products;
    }
}
