package com.livrariamabuko.Livraria.Mabuko.controller;

import com.livrariamabuko.Livraria.Mabuko.DTOs.AuthenticationDTO;
import com.livrariamabuko.Livraria.Mabuko.DTOs.SignUpDTO;
import com.livrariamabuko.Livraria.Mabuko.exceptions.DuplicatedEntityException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.model.User;
import com.livrariamabuko.Livraria.Mabuko.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody @Valid AuthenticationDTO authenticationDTO){
       var usernamePassowrd = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
       var auth = this.authenticationManager.authenticate(usernamePassowrd);
       return  ResponseEntity.ok().build();
    }

    // @PostMapping("/register")
    // public ResponseEntity register(@RequestBody @Valid SignUpDTO sign) {
    //     if (userRepository.findByUsername(sign.username()) != null) {
    //         throw new DuplicatedEntityException("Erro! Username já foi cadastrado no sistema ", "/api/users/register");
    //     }
    //     String encriptedPassword = new BCryptPasswordEncoder().encode(sign.password());
    //     User user = new User();
    //     BeanUtils.copyProperties(sign, user);
    //     userRepository.save(user);
    //     return ResponseEntity.status(HttpStatus.CREATED).body("Usuario cadastrado com Sucesso");
    // }
}
