package com.platzi_pizzeria.service.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDto {
    private int idPizza;
    private Double newPrice;
}
