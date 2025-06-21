package com.example.portfolio.securty;

import com.example.portfolio.Entity.User;
import com.example.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
    @Service
    @RequiredArgsConstructor
    @Slf4j
    public class CustomUserDetailsServiceImpl implements UserDetailsService {

        private final UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> {
                        log.error("User not found: {}", username);
                        return new UsernameNotFoundException("User not found: " + username);
                    });

            log.debug("User loaded: {}", username);
            return new CustomUserDetails(user);
        }
    }
