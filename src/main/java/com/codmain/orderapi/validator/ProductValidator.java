package com.codmain.orderapi.validator;

import com.codmain.orderapi.entitys.Product;
import com.codmain.orderapi.exceptions.ValideteServiceException;

public class  ProductValidator {
     public  static void save(Product product) {
        if(product.getName().isEmpty()) {
            throw new ValideteServiceException("name is necesario");
        }

        if (product.getPrice() == null) {
            throw new ValideteServiceException("the price is necesarry");
        }

        if (product.getPrice() < 0) {
            throw new ValideteServiceException("the price should be greater than zero");
        }

        if (!product.getPrice().isNaN()) {
            throw new ValideteServiceException("el precio no esta");
        }
    }
}
