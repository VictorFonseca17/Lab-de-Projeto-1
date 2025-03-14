package com.sistema_matricula.sistema_matricula.repository;

import com.sistema_matricula.sistema_matricula.entity.Disciplina;
import com.sistema_matricula.sistema_matricula.entity.Professor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    
    List<Disciplina> findAllByStatus(String status);

    List<Disciplina> findByProfessor(Professor professor);
}
