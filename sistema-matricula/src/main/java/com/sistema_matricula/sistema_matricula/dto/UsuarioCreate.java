package com.sistema_matricula.sistema_matricula.dto;

import com.sistema_matricula.sistema_matricula.Enum.Role;

import lombok.Data;

@Data
public class UsuarioCreate {
    private String username;
    private String password;
    private Role role;
}
