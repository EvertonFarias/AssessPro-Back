package com.AssessPro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.AssessPro.model.Produto;
import com.AssessPro.repository.ProdutoRepository;

@Service
public class ProdutoService {
	private final ProdutoRepository produtoRepository;
	
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
		
	} 
	
	public List<Produto> listarProdutos(){
		return produtoRepository.findAll();
	}
	public Optional<Produto> buscarProdutoPorId(Long id) {
		return produtoRepository.findById(id);
	}
	public Produto salvarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	public void deletarProduto(Long id) {
		produtoRepository.deleteById(id);
	}	

}
