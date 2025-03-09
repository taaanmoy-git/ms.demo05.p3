package com.mall.demo5.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mall.demo5.dto.ProductDTO;
import com.mall.demo5.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    // Purpose: Create a new product.
    
    @PostMapping(value = "/products/create", 
                 consumes = MediaType.APPLICATION_JSON_VALUE, 
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        logger.info("Creating new product: {}", productDTO);
        ResponseEntity<ProductDTO> response = productService.createProduct(productDTO);
        logger.info("Product created successfully with ID: {}", response.getBody().getProductId());
        return response;
    }

    // Purpose: Get a list of all products.
    
    @GetMapping(value = "/products", 
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        logger.info("Fetching all products");
        ResponseEntity<List<ProductDTO>> response = productService.getAllProducts();
        logger.info("Total products fetched: {}", response.getBody().size());
        return response;
    }
     
    // Purpose: Get a product by its ID.
    
    @GetMapping(value = "/products/{id}", 
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        logger.info("Fetching product by ID: {}", id);
        ResponseEntity<ProductDTO> response = productService.getProductById(id);
        logger.info("Product details: {}", response.getBody());
        return response;
    }
    
    // Purpose: Update a product by its ID.
    
    @PutMapping(value = "/products/update/{id}", 
                 consumes = MediaType.APPLICATION_JSON_VALUE, 
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        logger.info("Updating product with ID: {}", id);
        ResponseEntity<ProductDTO> response = productService.updateProductById(id, productDTO);
        logger.info("Product updated successfully: {}", response.getBody());
        return response;
    }
    
    // Purpose: Delete a product by its ID.
   
    @DeleteMapping(value = "/products/delete/{id}", 
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        logger.info("Deleting product with ID: {}", id);
        ResponseEntity<String> response = productService.deleteProduct(id);
        logger.info("Product deleted successfully with ID: {}", id);
        return response;
    }
    
    // Purpose: Check if a product is available (stock > 0).

    @GetMapping(value = "/products/checkAvailability/{productId}", 
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkProductAvailability(@PathVariable Long productId) {
        logger.info("Checking availability for product ID: {}", productId);
        ResponseEntity<Boolean> response = productService.checkAvailability(productId);
        logger.info("Product availability for ID {}: {}", productId, response.getBody());
        return response;
    }

    // Purpose: Decrease the product quantity when an order is placed.

    @PutMapping(value = "/products/decreaseQuantity/{productId}/{quantity}", 
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> decreaseProductQuantity(@PathVariable Long productId, @PathVariable Integer quantity) {
        logger.info("Decreasing quantity for product ID: {} by {}", productId, quantity);
        ResponseEntity<String> response = productService.decreaseQuantity(productId, quantity);
        logger.info("Product quantity decreased for ID: {}", productId);
        return response;
    }
    
    // Purpose: Increase the product quantity when an order is canceled.
  
    @PutMapping(value = "/products/increaseQuantity/{productId}/{quantity}", 
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> increaseProductQuantity(@PathVariable Long productId, @PathVariable Integer quantity) {
        logger.info("Increasing quantity for product ID: {} by {}", productId, quantity);
        ResponseEntity<String> response = productService.increaseQuantity(productId, quantity);
        logger.info("Product quantity increased for ID: {}", productId);
        return response;
    }
}
