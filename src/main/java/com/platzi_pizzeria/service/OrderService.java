package com.platzi_pizzeria.service;

import com.platzi_pizzeria.persistence.entity.OrderEntity;
import com.platzi_pizzeria.persistence.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll(){
        return orderRepository.findAll();
    }
}
