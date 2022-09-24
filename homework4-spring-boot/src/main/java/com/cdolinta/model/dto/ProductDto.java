package com.cdolinta.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;

    @NotBlank(message = "Can not be EMPTY !")
    private String title;

    @NotBlank(message = "Can not be EMPTY !")
    private String description;

    @Min(0)
    @Max(999)
    private Integer price;
}
