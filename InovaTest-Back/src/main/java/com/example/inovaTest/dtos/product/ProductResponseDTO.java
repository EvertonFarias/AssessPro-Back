package com.example.inovaTest.dtos.product;

import com.example.inovaTest.models.ProductModel;

public record ProductResponseDTO(String id, String name, Integer price) {
    public ProductResponseDTO(ProductModel product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}
