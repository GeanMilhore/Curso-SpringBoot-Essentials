package com.cursospring.springboot2.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()

                // http only = true /  means that front end software cannot get cookie
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

                // XSRF-TOKEN appears in cookies

                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();


        //                      CÓDIGO NO POSTMAN

        //        var xsrfCookie = postman.getResponseCookie("XSRF-TOKEN");
        //        postman.setEnvironmentVariable("x-xsrf-token", xsrfCookie.value);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password encoded {}", passwordEncoder.encode("test"));

        // Usuário em memória duranto o ciclo de vida da aplicação
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
