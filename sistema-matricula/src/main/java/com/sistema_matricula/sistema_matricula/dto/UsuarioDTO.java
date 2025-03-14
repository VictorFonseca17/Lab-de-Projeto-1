package com.sistema_matricula.sistema_matricula.dto;

import com.sistema_matricula.sistema_matricula.Enum.Role;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String username;
    private Role role;
}
