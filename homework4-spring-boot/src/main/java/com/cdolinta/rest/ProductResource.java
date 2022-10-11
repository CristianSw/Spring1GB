package com.cdolinta.rest;

import com.cdolinta.model.dto.ProductDto;
import com.cdolinta.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductResource {
    private final ProductService service;

    @GetMapping
    public List<ProductDto> listPage(
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            Model model) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(10);
        Page<ProductDto> productDto = service.findAllProducts(pageValue, sizeValue);
        return productDto.get().toList();
    }

    @GetMapping("/{id}")
    public ProductDto form(@PathVariable("id") long id, Model model) {
        return service.findProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @GetMapping("/new")
    public ProductDto addNewProduct(Model model) {
        service.saveProduct(new ProductDto());
        return new ProductDto();
    }

    @GetMapping("/delete/{id}")
    public ProductDto deleteProductById(@PathVariable long id) {
        service.deleteProductById(id);
        Optional<ProductDto> productById = service.findProductById(id);
        return productById.get();
    }

    @PostMapping
    public ProductDto saveProduct(ProductDto dto) {
        if (dto.getId() != null){
            throw new RuntimeException("Product id shouldn't have id");
        }
        service.saveProduct(dto);
        return dto;
    }

    @PostMapping("/update")
    public ProductDto updateProduct(ProductDto dto) {
        service.saveProduct(dto);
        return dto;
    }

}
