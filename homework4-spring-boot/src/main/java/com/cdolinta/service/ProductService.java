package com.cdolinta.service;

import com.cdolinta.model.dto.ProductDto;
import com.cdolinta.model.mapper.ProductDtoMapper;
import com.cdolinta.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductDtoMapper mapper;

    public Optional<ProductDto> findProductById(Long id){
        return repository.findById(id).map(mapper::map);
    }
    public Page<ProductDto> findAllProducts(int page, int size ){
        return repository.findAll(PageRequest.of(page, size)).map(mapper::map);
    }
    public void saveProduct(ProductDto dto){
        repository.save(mapper.map(dto));
    }
    public void deleteProductById(Long id){
        repository.deleteById(id);
    }
}
