package com.sapostolos.super_market.dtos;

import javax.validation.constraints.NotBlank;

public record RegisterUserDto(@NotBlank String email,
                              @NotBlank String password
                                ) {
}
