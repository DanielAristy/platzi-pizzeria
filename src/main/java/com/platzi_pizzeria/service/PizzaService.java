package com.platzi_pizzeria.service;

import com.platzi_pizzeria.persistence.entity.PizzaEntity;
import com.platzi_pizzeria.persistence.repository.PizzaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }


    public List<PizzaEntity> getAll() {
        log.info("PizzaService -> getAll");
        return pizzaRepository.findAll();
    }

    public PizzaEntity getPizza(Integer id) {
        return pizzaRepository.findById(id).orElse(null);
    }
}
