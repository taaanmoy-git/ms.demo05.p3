package com.mall.demo5.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.mall.demo5.dto.ProductDTO;
import com.mall.demo5.entity.Product;
import com.mall.demo5.exception.ProductNotFoundException;
import com.mall.demo5.exception.ProductOutOfStockException;
import com.mall.demo5.repository.ProductRepository;

public class ProductService {

	@Autowired
	ProductRepository productRepository;;

	// ✅ Create Product
	public ResponseEntity<ProductDTO> createProduct(ProductDTO productDTO) {
		Product product = productDTO.toEntity();
		Product savedProduct = productRepository.save(product);
		ProductDTO createdProductDTO = ProductDTO.createDTO(savedProduct);
		// return ResponseEntity.ok(ProductDTO.createDTO(savedProduct));
		return new ResponseEntity<>(createdProductDTO, HttpStatus.CREATED);
	}

	// ✅ Get All Products
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<Product> product = productRepository.findAll();
		List<ProductDTO> productDTO = product.stream().map(ProductDTO::createDTO).collect(Collectors.toList());
		return new ResponseEntity<List<ProductDTO>>(productDTO, HttpStatus.OK);
	}

	// ✅ Get Product By ID
	public ResponseEntity<ProductDTO> getProductById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		return ResponseEntity.ok(ProductDTO.createDTO(product));
	}

	// ✅ Update Product
	public ResponseEntity<ProductDTO> updateProductById(Long id, ProductDTO productDTO) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		product.setUpdatedAt(productDTO.getUpdatedAt());
		Product updatedProduct = productRepository.save(product);
		return new ResponseEntity<>(ProductDTO.createDTO(updatedProduct), HttpStatus.OK);
	}

	// ✅ Delete Product
	public ResponseEntity<String> deleteProduct(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		productRepository.delete(product);
		return ResponseEntity.ok("Product deleted successfully");
	}
	
	// -- Other Part Linked With Order Quantity -------------------------
	// -- If Some order given, product quantity will decrease,
	// -- If some order got cancelled, product quantity will increase 
	
	// ✅ Check Product Availability
	public ResponseEntity<Boolean> checkAvailability(Long productId) {
		Optional<Product> productOptional = productRepository.findById(productId);
		
		if(productOptional.isPresent() && productOptional.get().getQuantity() > 0) {
			return new ResponseEntity<>(true,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(false,HttpStatus.OK);
		}
	}
	
	// ✅ Decrease Product Quantity (On Order Placement)
    public ResponseEntity<String> decreaseQuantity(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found with ID: " + productId));

        if(product.getQuantity() < quantity) {
        	throw new ProductOutOfStockException("Product Out of Stock for ID: " + productId);
        }
        if (quantity> 0) {
        	product.setQuantity(product.getQuantity()-quantity);
        	product.setUpdatedAt(LocalDateTime.now());
        	productRepository.save(product);
        }else {
        	return new ResponseEntity<String>("Quantity must be greater than 0",HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Product Quantity Decreased Successfully");
    }
    
    // ✅ Increase Product Quantity (On Order Cancellation)
    public ResponseEntity<String> increaseQuantity(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found with ID: " + productId));
        if(quantity <= 0) {
        	return ResponseEntity.badRequest().body("Quantity must be greater than 0");
        }else {
        	product.setQuantity(product.getQuantity() + quantity);
        	product.setUpdatedAt(LocalDateTime.now());
            productRepository.save(product);
        }  
        return ResponseEntity.ok("Product Quantity Increased Successfully");
    }
}
