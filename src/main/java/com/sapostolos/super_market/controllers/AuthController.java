package com.sapostolos.super_market.controllers;

import com.sapostolos.super_market.authentication.AuthRequest;
import com.sapostolos.super_market.authentication.AuthResponse;
import com.sapostolos.super_market.authentication.JwtUtils;
import com.sapostolos.super_market.authentication.MyUserDetails;
import com.sapostolos.super_market.services.MyUserDetailsService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AuthController {
    
    private final PasswordEncoder passwordEncoder;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public AuthController(PasswordEncoder passwordEncoder, MyUserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/authenticate")
    public AuthResponse createAuthenticationToken(@RequestBody AuthRequest authRequest) throws BadCredentialsException {
        final MyUserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        if (!passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())){
            throw new BadCredentialsException("Failed to login");
        }
        final String jwt = jwtUtils.generateToken(userDetails);
        return new AuthResponse(jwt);
    }
}
