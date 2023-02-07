package com.codmain.orderapi.validator;

import com.codmain.orderapi.entitys.Product;

public class  ProductValidator {
     public  static void save(Product product) {
        if(product.getName().isEmpty()) {
            throw new RuntimeException("name is necesario");
        }

        if (product.getPrice() == null) {
            throw new RuntimeException("the price is necesarry");
        }

        if (product.getPrice() < 0) {
            throw new RuntimeException("the price should be greater than zero");
        }
    }
}
