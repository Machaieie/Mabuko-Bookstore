package com.livrariamabuko.Livraria.Mabuko.controller;

import com.livrariamabuko.Livraria.Mabuko.DTOs.AuthResponseDTO;
import com.livrariamabuko.Livraria.Mabuko.DTOs.AuthenticationDTO;
import com.livrariamabuko.Livraria.Mabuko.DTOs.LoginResponseDTO;
import com.livrariamabuko.Livraria.Mabuko.DTOs.SignUpDTO;
import com.livrariamabuko.Livraria.Mabuko.exceptions.DuplicatedEntityException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.EmptyDatabaseException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.model.Author;
import com.livrariamabuko.Livraria.Mabuko.model.User;
import com.livrariamabuko.Livraria.Mabuko.model.UserRole;
import com.livrariamabuko.Livraria.Mabuko.repository.UserRepository;
import com.livrariamabuko.Livraria.Mabuko.security.TokenService;
import com.livrariamabuko.Livraria.Mabuko.service.UserService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(),
                    authenticationDTO.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

          
            UserDetails userDetails = (UserDetails) auth.getPrincipal();


            
            Set<UserRole> roles = ((User) auth.getPrincipal()).getRoles();
            AuthResponseDTO authResponseDTO = new AuthResponseDTO();
            authResponseDTO.setId(((User) auth.getPrincipal()).getId());
            authResponseDTO.setName(((User) auth.getPrincipal()).getName());
            authResponseDTO.setUsername(userDetails.getUsername());
            authResponseDTO.setRoles(roles);



            
            Set<UserRole> roles = ((User) auth.getPrincipal()).getRoles();
            AuthResponseDTO authResponseDTO = new AuthResponseDTO();
            authResponseDTO.setId(((User) auth.getPrincipal()).getId());
            authResponseDTO.setName(((User) auth.getPrincipal()).getName());
            authResponseDTO.setUsername(userDetails.getUsername());
            authResponseDTO.setRoles(roles);


            var token = tokenService.generateToken((User) auth.getPrincipal());
            authResponseDTO.setToken(token);

            return ResponseEntity.ok(authResponseDTO);
        } catch (BadCredentialsException e) {
            throw new ResourceNotFoundException("Utilizador ou Senha incorrecta, tente novamente !");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpDTO requestDTO) throws Exception {
        User user = userService.registerUser(requestDTO);
        return ResponseEntity.ok().body(user.getConfirmationToken());
    }

    @GetMapping("/alluser")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



}

}

