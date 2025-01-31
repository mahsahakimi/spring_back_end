package edu.sharif.cc.config;

import edu.sharif.cc.filters.JwtFilter;
import edu.sharif.cc.utility.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()  // Use authorizeHttpRequests instead of authorizeRequests
            .requestMatchers("/auth/**").permitAll()  // Use permitAll directly
            .anyRequest().authenticated()  // Use authenticated directly
            .and()
            .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
            .csrf().disable();  // Disable CSRF protection for non-browser clients like APIs

        return http.build();
    }
}
