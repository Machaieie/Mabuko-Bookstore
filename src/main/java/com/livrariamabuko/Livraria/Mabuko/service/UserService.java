package com.livrariamabuko.Livraria.Mabuko.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.livrariamabuko.Livraria.Mabuko.DTOs.AuthenticationDTO;
import com.livrariamabuko.Livraria.Mabuko.DTOs.SignUpDTO;
import com.livrariamabuko.Livraria.Mabuko.model.User;
import com.livrariamabuko.Livraria.Mabuko.model.UserRole;
import com.livrariamabuko.Livraria.Mabuko.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.userRepository.findByUsernameAndEnabled(username, true)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public User registerUser(SignUpDTO request) throws Exception {
        if (userRepository.existsByUsername(request.username())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ja existe usuario com nome indicado");
        }
         
        User user = new User();
        user.setUsername(request.username());
        user.setName(request.name());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        String token = UUID.randomUUID().toString();
        user.setConfirmationToken(token);
        user.addRole(new UserRole(request.role()));
        userRepository.save(user);
        return user;

    }

    

}
