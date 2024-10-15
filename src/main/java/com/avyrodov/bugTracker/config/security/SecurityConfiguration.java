package com.avyrodov.bugTracker.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http
                .authorizeHttpRequests(httpRequests -> httpRequests
                        .requestMatchers(
                                new AntPathRequestMatcher("/css/**"),
                                new AntPathRequestMatcher("/js/**"),
                                new AntPathRequestMatcher("/vendor/**"),
                                new AntPathRequestMatcher("/img/**"),
                                new AntPathRequestMatcher("/fonts/**"),
                                new AntPathRequestMatcher("/svg/**"),
                                new AntPathRequestMatcher("/favicon.png"),
                                new AntPathRequestMatcher("/auth/**"),
                                new AntPathRequestMatcher("/error")
                        ).permitAll()
                        .anyRequest()
                        .authenticated());

        http.formLogin(form -> form
                .loginPage("/auth/login.html")
                .failureUrl("/auth/login.html?error")
                .successForwardUrl("/issue/issues.html")
                .defaultSuccessUrl("/issue/issues.html")
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }
}
