package com.sistema_matricula.sistema_matricula.dto;

import java.util.List;
import lombok.Data;

@Data
public class CursoDTO {
    private Long id;
    private Long cursoId;
    private String nome;
    private Integer creditos;
    private List<DisciplinaDTO> disciplinas;
}
