package com.example.inovaTest.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.inovaTest.dtos.product.ProductRequestDTO;
import com.example.inovaTest.dtos.product.ProductResponseDTO;
import com.example.inovaTest.models.ProductModel;
import com.example.inovaTest.repositories.ProductRepository;

import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid ProductRequestDTO body){
        ProductModel newProduct = new ProductModel(body);

        this.repository.save(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct); 
    }

    @GetMapping
    public ResponseEntity getAllProducts(){
        List<ProductResponseDTO> productList = this.repository.findAll().stream().map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productList);
    }
}
