package com.AssessPro.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.AssessPro.model.Produto;
import com.AssessPro.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    
    
        @GetMapping
        public List<Produto> listarProdutos() {
            return produtoService.listarProdutos();
        }
    
        // Example: Get product by ID
        @GetMapping("/{id}")
        public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
            return produtoService.buscarProdutoPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        }
    
        // Example: Create a new product
        @PostMapping
        public Produto salvarProduto(@RequestBody Produto produto) {
            return produtoService.salvarProduto(produto);
        }
    
        // Example: Delete a product by ID
        @DeleteMapping("/{id}")
        public void deletarProduto(@PathVariable Long id) {
            produtoService.deletarProduto(id);
        }

    
    
}
