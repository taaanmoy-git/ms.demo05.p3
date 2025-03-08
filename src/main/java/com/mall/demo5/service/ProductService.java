package com.mall.demo5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.mall.demo5.dto.ProductDTO;
import com.mall.demo5.entity.Product;
import com.mall.demo5.repository.ProductRepository;

public class ProductService {

	@Autowired
	ProductRepository productRepository;;
	
	public ResponseEntity<ProductDTO> createProduct(ProductDTO productDTO) {
		Product product = productDTO.toEntity();
		Product savedProduct = productRepository.save(product);
		ProductDTO createdProductDTO = ProductDTO.createDTO(savedProduct);
		// return ResponseEntity.ok(ProductDTO.createDTO(savedProduct));
		return new ResponseEntity<>(createdProductDTO, HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		
		return null;	
	}
}
