package com.platzi_pizzeria.service;

import com.platzi_pizzeria.persistence.entity.OrderEntity;
import com.platzi_pizzeria.persistence.projection.OrderSummary;
import com.platzi_pizzeria.persistence.repository.OrderRepository;
import com.platzi_pizzeria.service.dto.RandomOrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    public OrderRepository orderRepository;
    private final static String DELIVERY = "D";
    private final static String CARRYOUT = "C";
    private final static String ON_SITE = "S";


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> getAll() {
        return orderRepository.findAll();
    }

    public List<OrderEntity> getTodayOrders() {
        LocalDateTime dateAfter = LocalDate.now().atTime(0, 0);
        return this.orderRepository.findAllByDateAfter(dateAfter);
    }

    public List<OrderEntity> getOutSideOrders() {
        List<String> methods = List.of(DELIVERY, CARRYOUT);
        return this.orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String customerId) {
        return this.orderRepository.findCustomerOrder(customerId);
    }

    public OrderSummary getSummary(int orderId) {
        return this.orderRepository.findSummary(orderId);
    }

    @Transactional
    public boolean saveRandomOrder(RandomOrderDto randomOrderDto) {
        return this.orderRepository.saveRandomOrder(randomOrderDto.getIdCustomer(), randomOrderDto.getMethod());
    }
}
