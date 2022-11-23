package com.sapostolos.super_market.repositories;

import com.sapostolos.super_market.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
