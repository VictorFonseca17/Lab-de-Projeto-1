package com.sistema_matricula.sistema_matricula.controller;

import com.sistema_matricula.sistema_matricula.entity.Aluno;
import com.sistema_matricula.sistema_matricula.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    // 🔹 Listar os alunos matriculados nas disciplinas do professor
    @GetMapping("/{professorId}/alunos")
    public ResponseEntity<List<Aluno>> listarAlunosMatriculados(@PathVariable Long professorId) {
        List<Aluno> alunos = professorService.listarAlunosMatriculados(professorId);
        return ResponseEntity.ok(alunos);
    }
}
