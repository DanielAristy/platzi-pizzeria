package com.platzi_pizzeria.service;

import com.platzi_pizzeria.persistence.entity.CustomerEntity;
import com.platzi_pizzeria.persistence.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity getByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }
}
