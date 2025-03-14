package com.sistema_matricula.sistema_matricula.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import com.sistema_matricula.sistema_matricula.dto.DisciplinaDTO;
import com.sistema_matricula.sistema_matricula.entity.Curso;
import com.sistema_matricula.sistema_matricula.entity.Disciplina;

@Component
public class DisciplinaMapper {

    public DisciplinaDTO toDTO(Disciplina disciplina) {
        if (disciplina == null) {
            return null;
        }
        DisciplinaDTO dto = new DisciplinaDTO();
        dto.setId(disciplina.getId());
        dto.setNome(disciplina.getNome());
        dto.setTipoDisciplina(disciplina.getTipoDisciplina());
        dto.setStatus(disciplina.getStatus());

        if (disciplina.getCurso() != null) {
            dto.setCursoId(disciplina.getCurso().getId());
        }

        return dto;
    }

    public Disciplina toEntity(DisciplinaDTO dto, Curso curso) {
        if (dto == null) {
            return null;
        }
        Disciplina disciplina = new Disciplina();
        disciplina.setId(dto.getId());
        disciplina.setNome(dto.getNome());
        disciplina.setTipoDisciplina(dto.getTipoDisciplina());
        disciplina.setStatus(dto.getStatus());

        disciplina.setCurso(curso);

        return disciplina;
    }

    public List<DisciplinaDTO> toDTOList(List<Disciplina> disciplinas) {
        if (disciplinas == null) {
            return null;
        }
        return disciplinas.stream()
                          .map(this::toDTO)
                          .collect(Collectors.toList());
    }

    public List<Disciplina> toEntityList(List<DisciplinaDTO> disciplinaDTOs, Curso curso) {
        if (disciplinaDTOs == null) {
            return null;
        }
        return disciplinaDTOs.stream()
                             .map(dto -> toEntity(dto, curso))
                             .collect(Collectors.toList());
    }
}
