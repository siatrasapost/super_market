package com.sapostolos.super_market.repositories;

import com.sapostolos.super_market.entities.User;
import com.sapostolos.super_market.enums.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Configuration
public class InitUserRepo {
    @Bean
    CommandLineRunner addUsers(UserRepository repository) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(repository.findByEmail("admin@mail.com").isEmpty())
            repository.save(new User(UUID.randomUUID(), "admin@mail.com", bCryptPasswordEncoder.encode("password"), Role.ADMIN));
        if(repository.findByEmail("officer@mail.com").isEmpty())
            repository.save(new User(UUID.randomUUID(),"officer@mail.com",bCryptPasswordEncoder.encode("password"),Role.OFFICER));
        return null;
    }
}
