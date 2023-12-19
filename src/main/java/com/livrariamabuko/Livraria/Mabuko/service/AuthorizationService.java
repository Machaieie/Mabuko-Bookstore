package com.livrariamabuko.Livraria.Mabuko.service;

import com.livrariamabuko.Livraria.Mabuko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// @Service
// public class AuthorizationService implements UserDetailsService {

//     @Autowired
//     UserRepository userRepository;

//     // @Override
//     // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//     //     return userRepository.findByUsername(username);
//     // }
// }
