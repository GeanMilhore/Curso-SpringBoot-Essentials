package com.cursospring.springboot2.config;

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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *
     *      Cadeia de Filtros do Spring Security
     *
     *  BasicAuthenticationFilter
     *  -- verifica se possui authenticação do tipo base64
     *
     *  UsernamePasswordAuthenticationFilter
     *  -- verifica se na requisição tem usuário e password validos
     *
     *  DefaultLoginPageGeneratingFilter
     *  -- página padrão de login
     *
     *  DefaultLogoutPageGeneratingFilter
     * -- página padrão de logout
     *
     *  FilterSecurityInterceptor
     *  -- processo que checka se o usuário está atualizado
     *
     *  PRIORIDADES
     *
     *  Authentication - Authorization
     */

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
        auth.inMemoryAuthentication()
                .withUser("gean")
                .password(passwordEncoder.encode("senha"))
                .roles("USER","ADMIN")
                .and()
                .withUser("usuario")
                .password(passwordEncoder.encode("senha"))
                .roles("USER");
    }
}
