package com.sapostolos.super_market.enums;

public enum Role {
    ADMIN("ADMIN"),
    OFFICER("OFFICER");

    public final String label;

    Role(String label){
        this.label = label;
    }

    @Override
    public String toString(){
        return this.label;
    }
}
