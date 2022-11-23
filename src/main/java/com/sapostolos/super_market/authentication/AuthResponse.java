package com.sapostolos.super_market.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AuthResponse {
    private final String jwt;

    @JsonCreator
    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }


}
