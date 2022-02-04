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
                // antMatchers protege URLs
                .antMatchers("/animes/admin/**").hasRole("ADMIN")
                // a ordem importa | a role que seja mais restritiva vem primeiro
                .antMatchers("/animes/**").hasRole("USER")
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

        auth.inMemoryAuthentication()
                .withUser("admin2")
                .password(passwordEncoder.encode("senha"))
                .roles("USER","ADMIN")
                .and()
                .withUser("usuario2")
                .password(passwordEncoder.encode("senha"))
                .roles("USER");

        auth.userDetailsService(springUserDetailsService).passwordEncoder(passwordEncoder);
    }
}
