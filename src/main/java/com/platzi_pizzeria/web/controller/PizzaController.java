package com.platzi_pizzeria.web.controller;

import com.platzi_pizzeria.persistence.entity.PizzaEntity;
import com.platzi_pizzeria.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        log.info("PizzaController -> getAll");
        return ResponseEntity.ok(pizzas);
    }

    @GetMapping("{idPizza}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int idPizza) {
        log.info("PizzaController -> get");
        return ResponseEntity.ok(this.pizzaService.getPizza(idPizza));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza) {
        log.info("PizzaController -> add");
        if (pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        log.info("PizzaController -> update");
        if (pizza.getIdPizza() != null || this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza) {
        log.info("PizzaController -> delete");
        if (this.pizzaService.exists(idPizza)) {
            this.pizzaService.deleteById(idPizza);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
