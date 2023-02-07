package com.codmain.orderapi.services;

import com.codmain.orderapi.entitys.Product;
import com.codmain.orderapi.repositorys.ProductRepository;
import com.codmain.orderapi.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("no hay producto"));
    }

    public List<Product> findProductsAll(Pageable pageable) {
        return productRepository.findAll(pageable).toList();
    }

    @Transactional
    public Product save(Product product) {
        ProductValidator.save(product);
        return Objects.equals(product.getId(), null) ? productRepository.save(product) : updateProduct(product);
    }

    private Product updateProduct(Product product) {
        System.out.println(product.getId());
        Product updateProduct = productRepository
                .findById(product.getId()).orElseThrow(() -> new RuntimeException("no hay producto para actualizar"));

        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        productRepository.save(updateProduct);
        return updateProduct;
    }

    @Transactional
    public void deleted(Long id) {
        Product existProduct = productRepository
                .findById(id).orElseThrow(() -> new RuntimeException("no hay producto"));

        productRepository.delete(existProduct);
    }
}
