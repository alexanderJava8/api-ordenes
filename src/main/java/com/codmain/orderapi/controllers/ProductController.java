package com.codmain.orderapi.controllers;

import com.codmain.orderapi.entitys.Product;
import com.codmain.orderapi.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/products/{productId}")
    public ResponseEntity<Product> findById(@PathVariable("productId") Long productId) {
        Product product =  productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("no hay producto"));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(value = "products/")
    public ResponseEntity<List<Product>> findProductsAll() {
        List<Product> products = productRepository.findAll();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @PostMapping(value = "/products/")
    public ResponseEntity<Product> create(@RequestBody Product product) {
            Product productCreate = productRepository.save(product);

            return new ResponseEntity<>(productCreate, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/products/{productId}")
    public ResponseEntity<Void> deletedProducts(@PathVariable("productId") Long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
