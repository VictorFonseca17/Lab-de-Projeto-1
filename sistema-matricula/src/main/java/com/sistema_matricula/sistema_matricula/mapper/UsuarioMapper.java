package com.sistema_matricula.sistema_matricula.mapper;

import org.springframework.stereotype.Component;

import com.sistema_matricula.sistema_matricula.dto.UsuarioCreate;
import com.sistema_matricula.sistema_matricula.dto.UsuarioDTO;
import com.sistema_matricula.sistema_matricula.entity.Usuario;

@Component
public class UsuarioMapper {
    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setRole(usuario.getRole());
        return usuarioDTO;
    }

    public Usuario toEntity(UsuarioCreate usuarioCreate) {
        if (usuarioCreate == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioCreate.getUsername());
        usuario.setSenha(usuarioCreate.getPassword());
        usuario.setRole(usuarioCreate.getRole());
        return usuario;
    }
}
