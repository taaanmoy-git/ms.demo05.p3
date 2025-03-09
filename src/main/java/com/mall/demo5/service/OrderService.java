package com.mall.demo5.service;

import com.mall.demo5.dto.OrderDTO;
import com.mall.demo5.entity.Order;
import com.mall.demo5.exception.OrderNotFoundException;
import com.mall.demo5.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Get all orders
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if(orders.isEmpty()) {
        	throw new OrderNotFoundException("Order table empty, no data found.");
        }
        List<OrderDTO> orderDTOs = orders.stream()
                .map(OrderDTO::createDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderDTOs);
    }

    // Get order by ID
    public ResponseEntity<OrderDTO> getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));
        return new ResponseEntity<>(OrderDTO.createDTO(order),HttpStatus.OK);
    }

    // Delete an order by ID
    public ResponseEntity<String> deleteOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + orderId));

        orderRepository.deleteById(orderId);
        return new ResponseEntity<>("Order deleted successfully.",HttpStatus.OK);
    }
}
