package com.cursospring.springboot2.config;

import com.cursospring.springboot2.service.SpringUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SpringUserDetailsService springUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password encoded {}", passwordEncoder.encode("senha"));
        // config spring suporta multiplas autenticações ( Providers )
        // podemos trazer usuários de outros banco de dados ( exemplo atual: memory )
        auth.inMemoryAuthentication()
                .withUser("admin2")
                .password(passwordEncoder.encode("senha"))
                .roles("USER","ADMIN")
                .and()
                .withUser("usuario2")
                .password(passwordEncoder.encode("senha"))
                .roles("USER");

        // precisamos criar algo que nosso sistema vai reconhecer
        // por padrão o userDetailsService é apenas uma interface / precisamos prover a implementação do valor
        // quando estamos trazendo o loadByUsername ele vai precisar comparar o password { codificado }

        auth.userDetailsService(springUserDetailsService) // este método é executado no momento em que fazemos login
                .passwordEncoder(passwordEncoder); // graças ao polimorfismo utiliza nosso loadByUsename da nossa classe
                                                    // User details Customizada
    }
}
