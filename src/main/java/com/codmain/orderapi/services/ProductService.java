package com.codmain.orderapi.services;

import com.codmain.orderapi.entitys.Product;
import com.codmain.orderapi.repositorys.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public final class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("no hay producto"));
    }

    public List<Product> findProductsAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return Objects.equals(product.getId(), null) ? productRepository.save(product) : updateProduct(product);
    }

    private Product updateProduct(Product product) {
        Product updateProduct = productRepository
                .findById(product.getId()).orElseThrow(() -> new RuntimeException("no hay producto"));

        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        productRepository.save(updateProduct);
        return updateProduct;
    }
}
