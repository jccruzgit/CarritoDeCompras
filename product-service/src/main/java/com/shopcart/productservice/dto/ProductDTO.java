package com.shopcart.productservice.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;

}
