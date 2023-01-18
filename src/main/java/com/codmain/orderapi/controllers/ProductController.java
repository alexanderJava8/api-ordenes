package com.codmain.orderapi.controllers;

import com.codmain.orderapi.entitys.Product;
import com.codmain.orderapi.utils.Lists;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/products/{productId}")
    public Product findById(@PathVariable("productId") Long productId) {
            return products.stream()
                .filter(product -> Objects.equals(product.getId(), productId))
                .findAny()
                .orElse(new Product(0L, "defecto"));
    }

    @PostMapping(value = "/products/")
    public Product create(@RequestBody Product product) {
            Objects.requireNonNull(product, "product is null");

            products.add(product);
            return product;
    }

    @PutMapping(value = "/products/")
    public Product updateProduct(@RequestBody Product product) {
        Objects.requireNonNull(product, "product is null");
        for (Product product1 : products) {
            if (Objects.equals(product1.getId(), product.getId())) {
                 product1.setName(product.getName());
                 return product1;
            }
        }

        throw new RuntimeException("no hay ese producto");
    }

    @DeleteMapping(value = "/products/{productId}")
    public void deletedProducts(@PathVariable("productId") Long id) {
        Product existProduct = products.stream()
                .filter(product -> existProduct(product, id))
                .findFirst().orElseThrow();

        products.remove(existProduct);
    }

    private boolean existProduct(Product product, Long id) {
            return Objects.equals(product.getId(), id);
    }




}
