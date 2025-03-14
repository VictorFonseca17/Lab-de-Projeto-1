package com.sistema_matricula.sistema_matricula.mapper;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sistema_matricula.sistema_matricula.dto.ProfessorDTO;
import com.sistema_matricula.sistema_matricula.entity.Professor;

@Component
public class ProfessorMapper {
    public ProfessorDTO toDTO(Professor Professor) {
        if (Professor == null) {
            return null;
        }
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(Professor.getId());
        professorDTO.setNome(Professor.getUsername());
        return professorDTO;
    }

    public Professor toEntity(ProfessorDTO professorDTO) {
        if (professorDTO == null) {
            return null;
        }
        Professor Professor = new Professor();
        Professor.setId(professorDTO.getId());
        Professor.setUsername(professorDTO.getNome());
        return Professor;
    }

    public List<ProfessorDTO> toDTOList(List<Professor> professores) {
        if (professores == null) {
            return null;
        }
        return professores.stream()
                     .map(this::toDTO)
                     .collect(Collectors.toList());
    }

    public List<Professor> toEntityList(List<ProfessorDTO> professorDTOs) {
        if (professorDTOs == null) {
            return null;
        }
        return professorDTOs.stream()
                        .map(this::toEntity)
                        .collect(Collectors.toList());
    }
    
}
