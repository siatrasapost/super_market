package com.sapostolos.super_market.services;

import com.sapostolos.super_market.authentication.MyUserDetails;
import com.sapostolos.super_market.entities.User;
import com.sapostolos.super_market.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("No user with that email");
        } else {
            return new MyUserDetails(user.get().getId(), user.get().getEmail(), user.get().getPassword(), user.get().getRole());
        }
    }
}
