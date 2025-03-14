package com.sistema_matricula.sistema_matricula.repository;

import com.sistema_matricula.sistema_matricula.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}