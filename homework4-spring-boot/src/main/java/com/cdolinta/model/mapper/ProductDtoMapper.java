package com.cdolinta.model.mapper;

import com.cdolinta.model.Product;
import com.cdolinta.model.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductDtoMapper {

    ProductDto map(Product product);
    Product map(ProductDto dto);
}
