package com.mall.demo5.dto;


import java.time.LocalDateTime;
import com.mall.demo5.entity.Product;

public class ProductDTO {

    private Long productId;
    private String name;
    private double price;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public ProductDTO() {
		super();
	}

	public ProductDTO(Long productId, String name, double price, int quantity, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	
	
	@Override
	public String toString() {
		return "ProductDTO [productId=" + productId + ", name=" + name + ", price=" + price + ", quantity=" + quantity
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	public Product toEntity() {
        Product product = new Product();
        product.setProductId(this.productId);
        product.setName(this.name);
        product.setPrice(this.price);
        product.setQuantity(this.quantity);
        product.setCreatedAt(this.createdAt);
        product.setUpdatedAt(this.updatedAt);
        return product;
    }

    public static ProductDTO createDTO(Product product) {
        return new ProductDTO(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}