package com.sistema_matricula.sistema_matricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema_matricula.sistema_matricula.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
}
