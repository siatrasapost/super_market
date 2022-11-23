package com.sapostolos.super_market.dtos;

import java.util.UUID;

public record GetProductDto(UUID id,
                            String name,
                            Double price,
                            Integer quantity,
                            String aisle) {
}
