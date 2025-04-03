package com.platzi_pizzeria.web.controller;

import com.platzi_pizzeria.persistence.entity.PizzaEntity;
import com.platzi_pizzeria.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@Slf4j
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll() {
        List<PizzaEntity> pizzas = this.pizzaService.getAll();
        log.info("PizzaController -> getAll {}", pizzas);
        return ResponseEntity.ok(pizzas);
    }
}
