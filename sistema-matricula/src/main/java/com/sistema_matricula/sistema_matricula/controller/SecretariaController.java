package com.sistema_matricula.sistema_matricula.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistema_matricula.sistema_matricula.dto.AlunoDTO;
import com.sistema_matricula.sistema_matricula.dto.CursoDTO;
import com.sistema_matricula.sistema_matricula.dto.DisciplinaDTO;
import com.sistema_matricula.sistema_matricula.dto.ProfessorDTO;
import com.sistema_matricula.sistema_matricula.dto.UsuarioDTO;
import com.sistema_matricula.sistema_matricula.dto.UsuarioCreate;
import com.sistema_matricula.sistema_matricula.entity.Curso;
import com.sistema_matricula.sistema_matricula.entity.Disciplina;
import com.sistema_matricula.sistema_matricula.service.AuthService;
import com.sistema_matricula.sistema_matricula.service.SecretariaService;

@RestController
@RequestMapping("/secretaria")
public class SecretariaController {

    @Autowired
    private SecretariaService secretariaService;

        @Autowired
    private AuthService authService;

    @PostMapping("/cadastrarUsuario")
    public ResponseEntity<UsuarioDTO> register(@RequestBody UsuarioCreate usuarioCreate) {
        UsuarioDTO createdUser = authService.register(usuarioCreate);

        return ResponseEntity.created(null).body(createdUser);
    }

    @GetMapping("/listarAlunos")
    public ResponseEntity<List<AlunoDTO>> lerAluno() {
        List<AlunoDTO> alunos = secretariaService.getAlunos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/listarProfessores")
    public ResponseEntity<List<ProfessorDTO>> lerProfessor() {
        List<ProfessorDTO> professores = secretariaService.getProfessores();
        return ResponseEntity.ok(professores);
    }
    @PostMapping("/cadastrarCurso")
    public ResponseEntity<CursoDTO> cadastrarCurso(@RequestBody Curso curso) {
        return new ResponseEntity<>(secretariaService.cadastrarCurso(curso), HttpStatus.CREATED);
    }
    @GetMapping("/listarCursos")
    public ResponseEntity<List<CursoDTO>> listarCursos() {
        return new ResponseEntity<>(secretariaService.getCursos(), HttpStatus.OK);
    }
    @PostMapping("/cadastrarDisciplina")
    public ResponseEntity<DisciplinaDTO> cadastrarDisciplina(@RequestBody Disciplina disciplina){
        DisciplinaDTO novaDisciplina = secretariaService.cadastrarDisciplina(disciplina);
        return ResponseEntity.ok(novaDisciplina);
    }
    @GetMapping("/listarDisciplinas")
    public ResponseEntity<List<DisciplinaDTO>> lerDisciplinas() {
        List<DisciplinaDTO> professores = secretariaService.getDisciplinas();
        return ResponseEntity.ok(professores);
    }
    @PostMapping("/definirComoAtivas")
    public ResponseEntity<Void> definirDisciplinasOfertadas(@RequestBody List<Long> disciplinaIds) {
        secretariaService.definirDisciplinasOfertadas(disciplinaIds);

        return ResponseEntity.ok().build();
    }

}
