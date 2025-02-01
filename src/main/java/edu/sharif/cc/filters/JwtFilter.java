package edu.sharif.cc.filters;

import edu.sharif.cc.utility.JwtUtil; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;  // Utility class for handling JWT (you should have this class)

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain) throws ServletException, IOException {
        // Extract token from request header
        String token = extractToken(request);
        
        if (token != null && jwtUtil.validateToken(token)) {
            // Extract user information from the token
            String username = jwtUtil.extractUsername(token);

            // Load user details (You can customize this part based on your UserDetailsService)
            UserDetails userDetails = new User(username, "", new ArrayList<>());  // You may also add authorities here

            // Create the authentication token
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                userDetails, 
                null, 
                userDetails.getAuthorities()
            );

            // Set authentication into the security context
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        // Extract the token from the Authorization header
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);  // Remove "Bearer " prefix
        }
        return null;
    }

}