package com.sistema_matricula.sistema_matricula.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sistema_matricula.sistema_matricula.dto.SecretariaDTO;
import com.sistema_matricula.sistema_matricula.entity.Secretaria;

@Component
public class SecretariaMapper {

    public SecretariaDTO toDTO(Secretaria secretaria) {
        if (secretaria == null) {
            return null;
        }
        SecretariaDTO dto = new SecretariaDTO();
        dto.setId(secretaria.getId());
        dto.setNome(secretaria.getUsername());
        return dto;
    }

    public Secretaria toEntity(SecretariaDTO dto) {
        if (dto == null) {
            return null;
        }
        Secretaria secretaria = new Secretaria();
        secretaria.setId(dto.getId());
        secretaria.setUsername(dto.getNome());
        return secretaria;
    }

    public List<SecretariaDTO> toDTOList(List<Secretaria> secretarias) {
        if (secretarias == null) {
            return null;
        }
        return secretarias.stream()
                          .map(this::toDTO)
                          .collect(Collectors.toList());
    }

    public List<Secretaria> toEntityList(List<SecretariaDTO> secretariaDTOs) {
        if (secretariaDTOs == null) {
            return null;
        }
        return secretariaDTOs.stream()
                             .map(this::toEntity)
                             .collect(Collectors.toList());
    }
}
