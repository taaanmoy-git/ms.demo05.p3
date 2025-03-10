package com.mall.demo5.service;

/*
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mall.demo5.dto.OrderDTO;
import com.mall.demo5.entity.Order;
import com.mall.demo5.exception.OrderNotFoundException;
import com.mall.demo5.repository.OrderRepository;

*/
public class OrderCircuitBreakerService2 {
	/*
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(OrderCircuitBreakerService2.class);
	
	 // Place a new order
    @CircuitBreaker(name = "orderService", fallbackMethod = "placeOrderFallback")
    public Future<ResponseEntity<OrderDTO>> createOrder(OrderDTO orderDTO) {
        return CompletableFuture.supplyAsync(() -> {
            // Call Product Service to decrease quantity
            String productServiceUrl = "http://ProductMS/api/products/decreaseQuantity/"
                    + orderDTO.getProductId() + "/" + orderDTO.getQuantity();
            restTemplate.put(productServiceUrl, null);

            // Save the order
            Order order = orderDTO.toEntity();
            orderRepository.save(order);
            return ResponseEntity.ok(OrderDTO.createDTO(order));
        });
    }

    // Fallback method if Product Service fails during order placement
    public Future<ResponseEntity<OrderDTO>> placeOrderFallback(OrderDTO orderDTO, Throwable throwable) {
        logger.error("Product Service is down. Could not place order. Error: {}", throwable.getMessage());
        return CompletableFuture.completedFuture(ResponseEntity.internalServerError()
                .body(null));
    }
 
 // Cancel an order
    @CircuitBreaker(name = "orderService", fallbackMethod = "cancelOrderFallback")
    public Future<ResponseEntity<String>> cancelOrder(Long orderId) {
        return CompletableFuture.supplyAsync(() -> {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

            // Call Product Service to increase quantity
            String productServiceUrl = "http://ProductMS/api/products/increaseQuantity/"
                    + order.getProductId() + "/" + order.getQuantity();
            restTemplate.put(productServiceUrl, null);

            // Update order status to CANCELLED
            order.setStatus(Order.OrderStatus.CANCELLED);
            orderRepository.save(order);

           
            return ResponseEntity.ok("Order cancelled successfully");
        });
    }

    // Fallback method if Product Service fails during order cancellation
    public Future<ResponseEntity<String>> cancelOrderFallback(Long orderId, Throwable throwable) {
        logger.error("Product Service is down. Could not cancel order. Error: {}", throwable.getMessage());
        return CompletableFuture.completedFuture(ResponseEntity.internalServerError()
                .body("Failed to cancel order. Please try again later."));
    }
    */
}
