package com.sistema_matricula.sistema_matricula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema_matricula.sistema_matricula.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
}
