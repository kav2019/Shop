package ru.kovshov.shop.shop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService {
    UserDetailsService loadUserByUsername(String username) throws UsernameNotFoundException;
}
