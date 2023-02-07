package com.codmain.orderapi.converter;

import java.util.List;

public interface IConverter<E, D> {
     List<D> toProductDTO(List<E> product);
     List<E> toProductEntity(List<D> product);
     D toProductDTO(E product);
     E toProductEntity(D productDTO);
}
