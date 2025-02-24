package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.formLogin( form -> form
                .loginPage("/members/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/members/login/error")
                .usernameParameter("email")
                .passwordParameter("password")  // password 단어만 가능
                .permitAll());
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/", "/members/**").permitAll()
                .anyRequest().authenticated());  // 나머지 경로는 인증을 받을 것


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
