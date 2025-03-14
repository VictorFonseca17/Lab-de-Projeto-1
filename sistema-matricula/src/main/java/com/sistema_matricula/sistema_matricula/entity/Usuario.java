package com.sistema_matricula.sistema_matricula.entity;

import com.sistema_matricula.sistema_matricula.Enum.Role;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
    private String username;

    @NotBlank(message = "O campo senha é obrigatório")
    @Size(min = 3, max = 100, message = "O campo senha deve ter entre 3 e 100 caracteres")
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

}
