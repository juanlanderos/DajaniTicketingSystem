package com.jpa.dajaniTestDB.security.services;

import com.jpa.dajaniTestDB.entity.UserEntity;
import com.jpa.dajaniTestDB.service.repository.UserRepository;
import com.jpa.dajaniTestDB.service.serviceImplementation.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);

        return UserDetailsImpl.build(user);
    }
}
