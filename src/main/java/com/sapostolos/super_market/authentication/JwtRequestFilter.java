package com.sapostolos.super_market.authentication;

import com.sapostolos.super_market.services.MyUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final MyUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public JwtRequestFilter(MyUserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizatonHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        if (authorizatonHeader != null && authorizatonHeader.startsWith("Bearer ")){
            jwt = authorizatonHeader.substring(7);
            email = jwtUtils.extractEmail(jwt);
        }

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){

            MyUserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

            if(jwtUtils.validateToken(jwt, userDetails)){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
