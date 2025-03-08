package com.mall.demo5.service;

import java.util.List;
import java.util.stream.Collectors;

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
		List<Product> product = productRepository.findAll();
		List<ProductDTO> productDTO = product.stream()
						.map(ProductDTO::createDTO)
						.collect(Collectors.toList());
		return new ResponseEntity<List<ProductDTO>>(productDTO, HttpStatus.OK);	
	}
	
	public ResponseEntity<ProductDTO> getProductById(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Product not found"));
		return ResponseEntity.ok(ProductDTO.createDTO(product));	
	}
	
	public ResponseEntity<ProductDTO> updateProductById(Long id, ProductDTO productDTO) {
		Product product = productRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Product not found"));
		product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setUpdatedAt(productDTO.getUpdatedAt());
        Product updatedProduct = productRepository.save(product);
		return new ResponseEntity<>(ProductDTO.createDTO(updatedProduct),HttpStatus.OK);
	}
	
	public ResponseEntity<String> deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
