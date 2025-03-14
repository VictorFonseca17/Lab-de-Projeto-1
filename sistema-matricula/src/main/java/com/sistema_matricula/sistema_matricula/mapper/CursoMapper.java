package com.sistema_matricula.sistema_matricula.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sistema_matricula.sistema_matricula.dto.CursoDTO;
import com.sistema_matricula.sistema_matricula.dto.DisciplinaDTO;
import com.sistema_matricula.sistema_matricula.entity.Curso;
import com.sistema_matricula.sistema_matricula.entity.Disciplina;

@Component
public class CursoMapper {

    public CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setId(curso.getId());
        cursoDTO.setNome(curso.getNome());
        cursoDTO.setCreditos(curso.getCreditos());

        if (curso.getDisciplinas() != null) {
            cursoDTO.setDisciplinas(
                curso.getDisciplinas().stream()
                    .map(this::toDisciplinaDTO)
                    .collect(Collectors.toList())
            );
        }

        return cursoDTO;
    }

    public Curso toEntity(CursoDTO cursoDTO) {
        if (cursoDTO == null) {
            return null;
        }
        Curso curso = new Curso();
        curso.setId(cursoDTO.getId());
        curso.setNome(cursoDTO.getNome());
        curso.setCreditos(cursoDTO.getCreditos());

        if (cursoDTO.getDisciplinas() != null) {
            curso.setDisciplinas(
                cursoDTO.getDisciplinas().stream()
                    .map(this::toDisciplinaEntity)
                    .collect(Collectors.toList())
            );
        }

        return curso;
    }

    private DisciplinaDTO toDisciplinaDTO(Disciplina disciplina) {
        if (disciplina == null) return null;
        DisciplinaDTO dto = new DisciplinaDTO();
        dto.setId(disciplina.getId());
        dto.setNome(disciplina.getNome());
        return dto;
    }

    private Disciplina toDisciplinaEntity(DisciplinaDTO disciplinaDTO) {
        if (disciplinaDTO == null) return null;
        Disciplina disciplina = new Disciplina();
        disciplina.setId(disciplinaDTO.getId());
        disciplina.setNome(disciplinaDTO.getNome());
        return disciplina;
    }

    public List<CursoDTO> toDTOList(List<Curso> cursos) {
        if (cursos == null) {
            return null;
        }
        return cursos.stream()
                     .map(this::toDTO)
                     .collect(Collectors.toList());
    }

    public List<Curso> toEntityList(List<CursoDTO> cursoDTOs) {
        if (cursoDTOs == null) {
            return null;
        }
        return cursoDTOs.stream()
                        .map(this::toEntity)
                        .collect(Collectors.toList());
    }
}
