package com.sistema_matricula.sistema_matricula.controller;

import com.sistema_matricula.sistema_matricula.dto.DisciplinaDTO;
import com.sistema_matricula.sistema_matricula.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    // 🔹 Matricular o aluno em disciplinas (obrigatórias e optativas)
    @PostMapping("/matricular/{alunoId}")
    public ResponseEntity<String> matricularEmDisciplinas(@PathVariable Long alunoId, @RequestBody List<Long> disciplinaIds) {
        try {
            alunoService.matricularEmDisciplinas(alunoId, disciplinaIds);
            return ResponseEntity.ok("Matrícula realizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔹 Cancelar matrícula de uma disciplina
    @DeleteMapping("/cancelar/{alunoId}/{disciplinaId}")
    public ResponseEntity<String> cancelarMatricula(@PathVariable Long alunoId, @PathVariable Long disciplinaId) {
        try {
            alunoService.cancelarMatricula(alunoId, disciplinaId);
            return ResponseEntity.ok("Matrícula cancelada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🔹 Listar todas as disciplinas disponíveis para matrícula
    @GetMapping("/disciplinas-disponiveis")
    public ResponseEntity<List<DisciplinaDTO>> listarDisciplinasDisponiveis() {
        List<DisciplinaDTO> disciplinasDisponiveis = alunoService.listarDisciplinasDisponiveis();
        return ResponseEntity.ok(disciplinasDisponiveis);
    }
}
