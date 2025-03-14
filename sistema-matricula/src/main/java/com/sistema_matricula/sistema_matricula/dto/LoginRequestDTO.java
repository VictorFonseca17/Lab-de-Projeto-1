package com.sistema_matricula.sistema_matricula.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}