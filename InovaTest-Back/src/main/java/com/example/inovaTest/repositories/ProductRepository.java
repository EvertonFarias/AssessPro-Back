package com.example.inovaTest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inovaTest.models.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, String> {
}
