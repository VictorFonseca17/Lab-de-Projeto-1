package com.sistema_matricula.sistema_matricula.controller;

import com.sistema_matricula.sistema_matricula.dto.LoginRequestDTO;
import com.sistema_matricula.sistema_matricula.dto.UsuarioCreate;
import com.sistema_matricula.sistema_matricula.dto.UsuarioDTO;

import com.sistema_matricula.sistema_matricula.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        final String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> register(@RequestBody UsuarioCreate usuarioCreate) {
        UsuarioDTO createdUser = authService.register(usuarioCreate);

        return ResponseEntity.created(null).body(createdUser);
    }
}