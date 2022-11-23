package com.sapostolos.super_market.services;

import com.sapostolos.super_market.dtos.RegisterUserDto;
import com.sapostolos.super_market.repositories.UserRepository;
import com.sapostolos.super_market.utilities.Utilities;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(RegisterUserDto registerUserDto){
        var user = Utilities.registerUserDtoToUserConverter(registerUserDto);
        user.setPassword(passwordEncoder.encode(registerUserDto.password()));
        try{
            userRepository.save(user);
        }
        catch(DataIntegrityViolationException e){
            throw new DuplicateKeyException("Cannot have more than 1 user with this email");
        }
    }
}
