package com.mall.demo5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mall.demo5.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order , Long>{

}
