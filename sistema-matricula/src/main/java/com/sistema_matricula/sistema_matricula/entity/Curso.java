package com.sistema_matricula.sistema_matricula.entity;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
    @Schema(description = "Nome do curso", example = "Engenharia de Software")
    private String nome;

    @NotNull(message = "O campo é obrigatório")
    @Schema(description = "Quantidade de créditos do curso", example = "200")
    private Integer creditos;

    private Long cursoId;

    @OneToMany(mappedBy = "curso", orphanRemoval = true)
    @Schema(description = "Lista de disciplinas do curso")
    private List<Disciplina> disciplinas = new ArrayList<>();

}
