package com.sistema_matricula.sistema_matricula.mapper;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sistema_matricula.sistema_matricula.dto.AlunoDTO;
import com.sistema_matricula.sistema_matricula.entity.Aluno;

@Component
public class AlunoMapper {
    public AlunoDTO toDTO(Aluno aluno) {
        if (aluno == null) {
            return null;
        }
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setNome(aluno.getUsername());
        return alunoDTO;
    }

    public Aluno toEntity(AlunoDTO alunoDTO) {
        if (alunoDTO == null) {
            return null;
        }
        Aluno aluno = new Aluno();
        aluno.setId(alunoDTO.getId());
        aluno.setUsername(alunoDTO.getNome());
        return aluno;
    }

    public List<AlunoDTO> toDTOList(List<Aluno> alunos) {
        if (alunos == null) {
            return null;
        }
        return alunos.stream()
                     .map(this::toDTO)
                     .collect(Collectors.toList());
    }

    public List<Aluno> toEntityList(List<AlunoDTO> alunoDTOs) {
        if (alunoDTOs == null) {
            return null;
        }
        return alunoDTOs.stream()
                        .map(this::toEntity)
                        .collect(Collectors.toList());
    }
    
}
