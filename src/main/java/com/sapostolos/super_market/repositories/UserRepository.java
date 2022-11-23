package com.sapostolos.super_market.repositories;

import com.sapostolos.super_market.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
