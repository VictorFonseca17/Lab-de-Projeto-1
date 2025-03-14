package com.sistema_matricula.sistema_matricula.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sistema_matricula.sistema_matricula.dto.UsuarioCreate;
import com.sistema_matricula.sistema_matricula.dto.UsuarioDTO;
import com.sistema_matricula.sistema_matricula.entity.Usuario;
import com.sistema_matricula.sistema_matricula.mapper.UsuarioMapper;
import com.sistema_matricula.sistema_matricula.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public String authenticate(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        if (usuario != null && passwordEncoder.matches(password, usuario.getSenha())) {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withSubject(usuario.getUsername())
                    .withClaim("role", usuario.getRole().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                    .sign(algorithm);
        }
        return null;
    }

    // Método de registro
    public UsuarioDTO register(UsuarioCreate usuario) {
        // Verifica se o usuário já existe
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()){
            throw new IllegalArgumentException("entity.already_exists");
        }

        Usuario entity = usuarioMapper.toEntity(usuario);

        // Codifica a senha
        entity.setSenha(passwordEncoder.encode(entity.getSenha()));

        // Salva o novo usuário no banco de dados
        usuarioRepository.save(entity);

        return usuarioMapper.toDTO(entity);
    }
}