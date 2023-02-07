package com.codmain.orderapi.controllers;

import com.codmain.orderapi.converter.IConverter;
import com.codmain.orderapi.converter.ProductConverter;
import com.codmain.orderapi.dtos.ProductDTO;
import com.codmain.orderapi.entitys.Product;
import com.codmain.orderapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;
    private final IConverter<Product, ProductDTO> convert = new ProductConverter();

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products/{productId}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("productId") Long productId) {
        Product product =  productService.findById(productId);
        ProductDTO productDTO = convert.toProductDTO(product);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping(value = "products/")
    public ResponseEntity<List<ProductDTO>> findProductsAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                            @RequestParam(value = "size", defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size);
        List<Product> products = productService.findProductsAll(pageable);
        List<ProductDTO> productDTOS = convert.toProductDTO(products);

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/products/")
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product) {
            Product productCreate = productService.save(convert.toProductEntity(product));
            ProductDTO productDTO = convert.toProductDTO(productCreate);

            return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/products/{productId}")
    public ResponseEntity<Void> deletedProducts(@PathVariable("productId") Long id) {
        productService.deleted(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
