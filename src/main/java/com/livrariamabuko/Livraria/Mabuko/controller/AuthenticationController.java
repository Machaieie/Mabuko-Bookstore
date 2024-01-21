package com.livrariamabuko.Livraria.Mabuko.controller;

import com.livrariamabuko.Livraria.Mabuko.DTOs.AuthenticationDTO;
import com.livrariamabuko.Livraria.Mabuko.DTOs.LoginResponseDTO;
import com.livrariamabuko.Livraria.Mabuko.DTOs.SignUpDTO;
import com.livrariamabuko.Livraria.Mabuko.exceptions.DuplicatedEntityException;
import com.livrariamabuko.Livraria.Mabuko.exceptions.ResourceNotFoundException;
import com.livrariamabuko.Livraria.Mabuko.model.User;
import com.livrariamabuko.Livraria.Mabuko.repository.UserRepository;
import com.livrariamabuko.Livraria.Mabuko.security.TokenService;
import com.livrariamabuko.Livraria.Mabuko.service.UserService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
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
            // Cria um objeto de autenticação com as credenciais fornecidas
            var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(),
                    authenticationDTO.password());

            // Autentica o usuário
            var auth = this.authenticationManager.authenticate(usernamePassword);

            // Obtém informações sobre o usuário autenticado
            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            // Adiciona informações do usuário ao corpo da resposta
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("id", ((User) auth.getPrincipal()).getId());
            responseBody.put("name", ((User) auth.getPrincipal()).getName());
            responseBody.put("username", userDetails.getUsername());
            responseBody.put("roles", userDetails.getAuthorities());
            // Gera o token JWT para o usuário autenticado
            var token = tokenService.generateToken((User) auth.getPrincipal());

            // Adiciona o token ao corpo da resposta
            responseBody.put("token", token);

            // Retorna a resposta com as informações do usuário e o token
            return ResponseEntity.ok(responseBody);
        } catch (BadCredentialsException e) {
            // Captura a exceção de credenciais inválidas
            throw new ResourceNotFoundException("Utilizador ou Senha incorretos, tente novamente!");
        } catch (Exception e) {
            // Logs para verificar mensagens de erro ou exceções
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpDTO requestDTO) throws Exception {
        User user = userService.registerUser(requestDTO);
        return ResponseEntity.ok().body(user.getConfirmationToken());
    }

}
