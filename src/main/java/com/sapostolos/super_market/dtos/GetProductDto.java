package com.sapostolos.super_market.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

public record GetProductDto(UUID id,
                            @NotBlank String name,
                            @Positive Double price,
                            @PositiveOrZero Integer quantity,
                            String aisle) {
}
