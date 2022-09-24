package com.cdolinta.controller;


import com.cdolinta.model.dto.ProductDto;
import com.cdolinta.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;


    @GetMapping
    public String listPage(
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            Model model) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(10);

        model.addAttribute("products", service.findAllProducts(pageValue, sizeValue));
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", service.findProductById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        service.deleteProductById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String saveProduct(@Valid @ModelAttribute(value = "product") ProductDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        service.saveProduct(dto);
        return "redirect:/product";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute(value = "product") ProductDto dto) {
        service.saveProduct(dto);
        return "redirect:/product";
    }

}
