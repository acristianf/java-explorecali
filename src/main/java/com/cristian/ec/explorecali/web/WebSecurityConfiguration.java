package com.cristian.ec.explorecali.web;

import com.cristian.ec.explorecali.service.ExploreCaliUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    private final ExploreCaliUserDetailsService exploreCaliUserDetailsService;

    public WebSecurityConfiguration(ExploreCaliUserDetailsService exploreCaliUserDetailsService) {
        this.exploreCaliUserDetailsService = exploreCaliUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        (authz) -> authz
                                .requestMatchers("/packages/**").permitAll()
                                .requestMatchers("/tours/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/users/registration").permitAll()
                                .requestMatchers(HttpMethod.POST, "/users/registration/**").permitAll()
                                .anyRequest().authenticated())
                .userDetailsService(exploreCaliUserDetailsService)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
