package com.ocado.basket.data;

import com.ocado.basket.domain.Product;

import java.util.List;

public interface FileReader {
    List<Product> listOfProducts(String AbsoluteFilePath);
}
