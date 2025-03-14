package com.sistema_matricula.sistema_matricula.dto;

import com.sistema_matricula.sistema_matricula.Enum.TipoDisciplina;
import lombok.Data;

import java.util.List;

import com.sistema_matricula.sistema_matricula.Enum.Status;
@Data
public class DisciplinaDTO {
    private Long id;
    private String nome;
    private TipoDisciplina tipoDisciplina;
    private Status status;
    private Long cursoId;
    private List<Long> disciplinaIds;
    public List<Long> getDisciplinaIds() {
        return disciplinaIds;
    }

    public void setDisciplinaIds(List<Long> disciplinaIds) {
        this.disciplinaIds = disciplinaIds;
    }
}
