package com.mall.demo5.dto;

import java.time.LocalDateTime;
import com.mall.demo5.entity.Order;

public class OrderDTO {

    private Long orderId;
    private Long customerId;
    private Long productId;
    private Integer quantity;
    private String status;// PLACED,CANCELLED
    private LocalDateTime orderAt;
    
    //Product Part, Some Part to Show Or Send  Response

    private String productName;
    private double productPrice;

    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	// Order Part
	public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(LocalDateTime orderAt) {
        this.orderAt = orderAt;
    }

    @Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", customerId=" + customerId + ", productId=" + productId
				+ ", quantity=" + quantity + ", status=" + status + ", orderAt=" + orderAt + "]";
	}

	// Method to convert DTO to Entity
    public Order toEntity() {
        Order order = new Order();
        order.setOrderId(this.orderId);
        order.setCustomerId(this.customerId);
        order.setProductId(this.productId);
        order.setQuantity(this.quantity);
        order.setStatus(Order.OrderStatus.valueOf(this.status));
        order.setOrderAt(this.orderAt);
        return order;
    }

    // Method to convert Entity to DTO
    public static OrderDTO createDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setCustomerId(order.getCustomerId());
        dto.setProductId(order.getProductId());
        dto.setQuantity(order.getQuantity());
        dto.setStatus(order.getStatus().name());
        dto.setOrderAt(order.getOrderAt());
        return dto;
    }
    
 // ✅ Create DTO from Order Entity + ProductDTO
    public static OrderDTO createDTO(Order order, ProductDTO productDTO) {
    	OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setCustomerId(order.getCustomerId());
        dto.setProductId(order.getProductId());
        dto.setQuantity(order.getQuantity());
        dto.setStatus(order.getStatus().name());
        dto.setOrderAt(order.getOrderAt());

        // ✅ Fetch from ProductDTO
        dto.setProductName(productDTO.getName());
        dto.setProductPrice(productDTO.getPrice());

        return dto;
    }
}