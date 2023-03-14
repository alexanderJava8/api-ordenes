package com.codmain.orderapi.services;

import com.codmain.orderapi.entitys.Product;
import com.codmain.orderapi.exceptions.GeneralServicesExceptions;
import com.codmain.orderapi.exceptions.NoDataFoundException;
import com.codmain.orderapi.exceptions.ValideteServiceException;
import com.codmain.orderapi.repositorys.ProductRepository;
import com.codmain.orderapi.validator.ProductValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long productId) {
        try {
            return productRepository.findById(productId)
                    .orElseThrow(() -> new NoDataFoundException("no hay producto"));
        } catch (ValideteServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServicesExceptions(e.getMessage(), e);
        }
    }

    public List<Product> findProductsAll(Pageable pageable) {
        try {
            return productRepository.findAll(pageable).toList();
        } catch (ValideteServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServicesExceptions(e.getMessage(), e);
        }
    }

    @Transactional
    public Product save(Product product) {
        try {
            ProductValidator.save(product);
            return Objects.equals(product.getId(), null) ? productRepository.save(product) : updateProduct(product);
        } catch (ValideteServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServicesExceptions(e.getMessage(), e);
        }
    }

    private Product updateProduct(Product product) {
        System.out.println(product.getId());
        Product updateProduct = productRepository
                .findById(product.getId()).orElseThrow(() -> new NoDataFoundException("no hay producto para actualizar"));

        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        productRepository.save(updateProduct);
        return updateProduct;
    }

    @Transactional
    public void deleted(Long id) {
        try {
            Product existProduct = productRepository
                    .findById(id).orElseThrow(() -> new NoDataFoundException("no hay producto"));
            productRepository.delete(existProduct);

        } catch (ValideteServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);

        } catch (Exception e) {
            log.info(e.getMessage(),e );
            throw new GeneralServicesExceptions(e.getMessage(),e);
        }
    }
}
