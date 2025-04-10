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
        log.info("PizzaService countByVeganTrue  {} ", pizzaRepository.countByVeganTrue());
        return pizzaRepository.findAll();
    }

    public List<PizzaEntity> getAvailable() {
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    public PizzaEntity getPizza(int id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity getByName(String name) {
        return pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredients) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredients);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    public Boolean exists(int idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }

    public void deleteById(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }
}
