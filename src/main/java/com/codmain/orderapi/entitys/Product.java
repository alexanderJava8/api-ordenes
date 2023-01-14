package com.codmain.orderapi.entitys;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {
    private Long id;
    private String name;

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
