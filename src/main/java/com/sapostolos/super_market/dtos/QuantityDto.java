package com.sapostolos.super_market.dtos;

import javax.validation.constraints.PositiveOrZero;

public record QuantityDto(@PositiveOrZero Integer quantity) {
}
