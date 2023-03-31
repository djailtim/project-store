package com.example.projectstore.api.system;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Profile("security")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {


    private final JwtAuthFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    private static final String[] AUTH_ALLOWLIST = {
        // -- Swagger UI v3
        "/v3/api-docs/**",
        "v3/api-docs/**",
        "/swagger-ui/**",
        "swagger-ui/**",
        "/swagger-ui.html",
        "swagger-ui.html",
        // Actuators
        "/actuator/**",
        "/health/**",
        // Login
        "/auth/login",
            "/storelogin",
            "/user",
            "/products/**"
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(requests ->
                requests
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers(AUTH_ALLOWLIST).permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/admin/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/admin/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/admin/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/orderline/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/user/orderline/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/user/orderline/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/user/orderline/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/user/order/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/user/order/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/user/order/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
            .headers().frameOptions().disable()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout()
                .logoutUrl("/auth/logout").addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) ->
                        SecurityContextHolder.clearContext())
        ;

        return http.build();
    }

}
