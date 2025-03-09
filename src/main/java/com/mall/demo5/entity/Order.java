package com.mall.demo5.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long customerId;
    private Long productId;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime orderAt;
    
//    public Order() {
//		super();
//	}
//    
//	public Order(Long orderId, Long customerId, Long productId, Integer quantity, OrderStatus status,
//			LocalDateTime orderAt) {
//		super();
//		this.orderId = orderId;
//		this.customerId = customerId;
//		this.productId = productId;
//		this.quantity = quantity;
//		this.status = status;
//		this.orderAt = orderAt;
//	}

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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
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
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", productId=" + productId + ", quantity="
				+ quantity + ", status=" + status + ", orderAt=" + orderAt + "]";
	}



	public enum OrderStatus {
        PLACED,
        CANCELLED
    }
}
