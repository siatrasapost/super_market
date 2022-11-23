package com.sapostolos.super_market.dtos;

import javax.validation.constraints.Positive;

public record QuantityDto(@Positive Integer quantity) {
}
