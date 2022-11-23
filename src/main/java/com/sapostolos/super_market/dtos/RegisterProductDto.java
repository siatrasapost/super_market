package com.sapostolos.super_market.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public record RegisterProductDto(@NotBlank String name,
                                 @Positive Double price,
                                 String aisle) {

}
