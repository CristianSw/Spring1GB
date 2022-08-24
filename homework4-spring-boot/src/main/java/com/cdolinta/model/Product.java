package com.cdolinta.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
public class Product {
    private Long id;

    @NotBlank(message = "Can not be EMPTY !")
    private String title;

    @NotBlank(message = "Can not be EMPTY !")
    private String description;

    @Min(0)
    @Max(999)
    private Integer price;
}
