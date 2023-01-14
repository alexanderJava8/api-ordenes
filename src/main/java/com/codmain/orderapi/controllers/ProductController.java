package com.codmain.orderapi.controllers;

import com.codmain.orderapi.entitys.Product;
import com.codmain.orderapi.utils.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private final List<Product> products = new ArrayList<>(10);

    public ProductController() {
        for (long i = 0; i < 10; i++) {
            Product product = new Product(i, "producto : " + i);
            products.add(product);
        }
    }

    @GetMapping(value = "/products")
    public List<Product> products() {
        return products;
    }


}
