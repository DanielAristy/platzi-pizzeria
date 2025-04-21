package com.platzi_pizzeria.service;

import com.platzi_pizzeria.persistence.entity.PizzaEntity;
import com.platzi_pizzeria.persistence.repository.PizzaPagSortRepository;
import com.platzi_pizzeria.persistence.repository.PizzaRepository;
import com.platzi_pizzeria.service.dto.UpdatePizzaPriceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }


    public Page<PizzaEntity> getAll(int page, int size) {
        log.info("PizzaService -> getAll");
        log.info("PizzaService countByVeganTrue  {} ", pizzaRepository.countByVeganTrue());
        Pageable pageable = PageRequest.of(page, size);
        return pizzaPagSortRepository.findAll(pageable);
    }

    public Page<PizzaEntity> getAvailable(int page, int size, String sortBy, String sortDirections) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirections), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageable);
    }

    public PizzaEntity getPizza(int id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity getByName(String name) {
        return pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredients) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredients);
    }

    public List<PizzaEntity> getCheapest(double price) {
        return pizzaRepository.findTop3ByAvailableTrueAndPriceIsLessThanEqualOrderByPriceAsc(price);
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

    @Transactional
    public void updatePrice(UpdatePizzaPriceDto pizzaPriceDto){
        this.pizzaRepository.updatePrice(pizzaPriceDto);
    }
}
