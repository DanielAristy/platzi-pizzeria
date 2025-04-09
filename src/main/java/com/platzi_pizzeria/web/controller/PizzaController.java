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

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int idPizza) {
        log.info("PizzaController -> get");
        return ResponseEntity.ok(this.pizzaService.getPizza(idPizza));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
        log.info("PizzaController -> getByName");
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable() {
        log.info("PizzaController -> getAvailable");
        return ResponseEntity.ok(this.pizzaService.getAvailable());
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByWith(@PathVariable String ingredient) {
        log.info("PizzaController -> getByWith");
        return ResponseEntity.ok(this.pizzaService.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByWithout(@PathVariable String ingredient) {
        log.info("PizzaController -> getByWithout");
        return ResponseEntity.ok(this.pizzaService.getWithout(ingredient));
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
