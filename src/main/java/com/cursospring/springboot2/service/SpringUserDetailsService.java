package com.cursospring.springboot2.service;

import com.cursospring.springboot2.repository.SpringUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpringUserDetailsService implements UserDetailsService {
    private final SpringUserRepository springUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(springUserRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("SpringUser Not Found"));
    }
}
