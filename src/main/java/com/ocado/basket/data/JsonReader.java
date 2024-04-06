package com.ocado.basket.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocado.basket.domain.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonReader implements FileReader{
    @Override
    public List<Product> listOfProducts(String AbsoluteFilePath) {
        List<Product> configurationList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, List<String>> mapOfProducts = objectMapper.readValue(new File(AbsoluteFilePath), Map.class);
            mapOfProducts.forEach((key, value) -> configurationList.add(new Product(key, value)));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return configurationList;

    }
}
