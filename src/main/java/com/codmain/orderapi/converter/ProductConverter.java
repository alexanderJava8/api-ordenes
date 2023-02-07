package com.codmain.orderapi.converter;

import com.codmain.orderapi.dtos.ProductDTO;
import com.codmain.orderapi.entitys.Product;

import java.util.List;
import java.util.Objects;

public class ProductConverter implements IConverter<Product, ProductDTO> {
    @Override
    public List<ProductDTO> toProductDTO(List<Product> product) {
        return product.stream().map(this::toDTO).toList();
    }

    @Override
    public List<Product> toProductEntity(List<ProductDTO> product) {
        return product.stream().map(this::toEntity).toList();
    }

    @Override
    public ProductDTO toProductDTO(Product product) {
        return toDTO(product);
    }

    @Override
    public Product toProductEntity(ProductDTO productDTO) {
        return toEntity(productDTO);
    }

    private ProductDTO toDTO(Product product) {
        Objects.requireNonNull(product);

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice()).build();
    }

    private Product toEntity(ProductDTO productDTO) {
        Objects.requireNonNull(productDTO);

        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
    }


}
