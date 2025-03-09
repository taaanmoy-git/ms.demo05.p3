package com.mall.demo5.exception;

public class ProductOutOfStockException extends RuntimeException {
	
	public ProductOutOfStockException(String message) {
		super(message);
	}
}
