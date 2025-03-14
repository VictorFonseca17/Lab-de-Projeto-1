package com.sistema_matricula.sistema_matricula.service;

import com.sistema_matricula.sistema_matricula.entity.Professor;
import com.sistema_matricula.sistema_matricula.entity.Disciplina;
import com.sistema_matricula.sistema_matricula.entity.Aluno;
import com.sistema_matricula.sistema_matricula.repository.ProfessorRepository;
import com.sistema_matricula.sistema_matricula.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    // Método para listar os alunos matriculados nas disciplinas de um professor
    public List<Aluno> listarAlunosMatriculados(Long professorId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        List<Disciplina> disciplinas = disciplinaRepository.findByProfessor(professor);

        return disciplinas.stream()
                .flatMap(disciplina -> disciplina.getAlunos().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
