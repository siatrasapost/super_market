package com.sapostolos.super_market.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "name", unique = true)
    private String name;    //unique
    private Double price;
    private Integer quantity;
    private String aisle;

    public Product(){}

    public Product(UUID id, String name, Double price, Integer quantity, String aisle) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.aisle = aisle;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }
}
