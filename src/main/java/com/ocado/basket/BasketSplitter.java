package com.ocado.basket;
import com.ocado.basket.data.JsonReader;
import com.ocado.basket.domain.DeliveryMethod;
import com.ocado.basket.domain.Product;

import java.util.*;
import java.util.stream.Collectors;

public class BasketSplitter {
    private List<Product> configurationList = new ArrayList<>();
    public BasketSplitter(String absolutePathToConfigFile) {
        if (absolutePathToConfigFile.isEmpty()) throw new IllegalArgumentException("Path to file is empty string");

        JsonReader jsonReader = new JsonReader();
        this.configurationList = jsonReader.listOfProducts(absolutePathToConfigFile);
    }
    public Map<String, List<String>> split(List<String> items) {
        if (items.isEmpty()) throw new IllegalArgumentException("List of items is empty");

        List<Product> basket = createBasket(items);
        List<DeliveryMethod> deliveryMethods = getDeliveryMethods(basket);
        deliveryMethods.sort(new Comparator<DeliveryMethod>() {
            @Override
            public int compare(DeliveryMethod o1, DeliveryMethod o2) {
                return Integer.compare(o2.getProducts().size(), o1.getProducts().size());
            }
        });

        for (int i = 0; i < deliveryMethods.size(); i++) {
            for (int j = i + 1; j < deliveryMethods.size(); j++) {
                deliveryMethods.get(j).getProducts().removeAll(deliveryMethods.get(i).getProducts());
            }
        }

        return createResultMap(deliveryMethods);
    }

    private static Map<String, List<String>> createResultMap(List<DeliveryMethod> deliveryMethods) {
        Map<String, List<String>> resultMap= new HashMap<>();
        for (DeliveryMethod deliveryMethod: deliveryMethods) {
            if (!deliveryMethod.getProducts().isEmpty()) {
                resultMap.put(deliveryMethod.getName(), deliveryMethod.getProducts());
            }
        }
        return resultMap;
    }

    private static List<DeliveryMethod> getDeliveryMethods(List<Product> basket) {
        //Creating list of delivery methods consisting of string names
        List<String> namesOfDeliveryMethodsWithRepeating = new ArrayList<>();
        basket.forEach(x -> namesOfDeliveryMethodsWithRepeating.addAll(x.getDeliveryMethods()));
        //getting all delivery methods from all products causing repetition of certain positions, but we need list with unique elements only
        List<String> uniqueNamesOfDeliveryMethods = namesOfDeliveryMethodsWithRepeating.stream().distinct().collect(Collectors.toList());

        return getDeliveryMethodsObjectsList(basket, uniqueNamesOfDeliveryMethods);
    }

    private static List<DeliveryMethod> getDeliveryMethodsObjectsList(List<Product> basket, List<String> namesOfDeliveryMethods) {
        List<DeliveryMethod> deliveryMethods = new ArrayList<>();
        for (String name: namesOfDeliveryMethods) {
            List<String> products = new ArrayList<>();
            for (Product product: basket) {
                if (product.getDeliveryMethods().contains(name)) {
                    products.add(product.name());
                }
            }
            deliveryMethods.add(new DeliveryMethod(name, products));
        }
        return deliveryMethods;
    }

    private List<Product> createBasket(List<String> items) {
        List<Product> list = new ArrayList<>();
        for (String item: items) {
            for (Product position: configurationList) {
                if (position.name().equals(item)) {
                    list.add(position);
                }
            }
        }
        return list;
    }

    public List<Product> getConfigurationList() {
        return configurationList;
    }

}