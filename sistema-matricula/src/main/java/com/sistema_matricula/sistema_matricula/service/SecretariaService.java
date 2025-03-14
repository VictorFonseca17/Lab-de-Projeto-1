package com.sistema_matricula.sistema_matricula.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema_matricula.sistema_matricula.dto.AlunoDTO;
import com.sistema_matricula.sistema_matricula.dto.CursoDTO;
import com.sistema_matricula.sistema_matricula.dto.DisciplinaDTO;
import com.sistema_matricula.sistema_matricula.dto.ProfessorDTO;
import com.sistema_matricula.sistema_matricula.dto.UsuarioCreate;
import com.sistema_matricula.sistema_matricula.dto.UsuarioDTO;
import com.sistema_matricula.sistema_matricula.entity.Aluno;
import com.sistema_matricula.sistema_matricula.entity.Curso;
import com.sistema_matricula.sistema_matricula.entity.Disciplina;
import com.sistema_matricula.sistema_matricula.entity.Professor;
import com.sistema_matricula.sistema_matricula.entity.Usuario;
import com.sistema_matricula.sistema_matricula.mapper.AlunoMapper;
import com.sistema_matricula.sistema_matricula.mapper.CursoMapper;
import com.sistema_matricula.sistema_matricula.mapper.DisciplinaMapper;
import com.sistema_matricula.sistema_matricula.mapper.ProfessorMapper;
import com.sistema_matricula.sistema_matricula.mapper.UsuarioMapper;
import com.sistema_matricula.sistema_matricula.repository.AlunoRepository;
import com.sistema_matricula.sistema_matricula.repository.CursoRepository;
import com.sistema_matricula.sistema_matricula.repository.DisciplinaRepository;
import com.sistema_matricula.sistema_matricula.repository.ProfessorRepository;
import com.sistema_matricula.sistema_matricula.repository.UsuarioRepository;

@Service
public class SecretariaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoMapper alunoMapper;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ProfessorMapper professorMapper;

    @Autowired
    private CursoRepository cursoRepository; // Repositório de Curso

    @Autowired
    private CursoMapper cursoMapper; // Mapper de Curso

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private DisciplinaMapper disciplinaMapper;

    // 🔹 Cadastrar um aluno
    public AlunoDTO cadastrarAluno(Aluno aluno) {
        return alunoMapper.toDTO(alunoRepository.save(aluno));
    }

    // 🔹 Listar todos os alunos
    public List<AlunoDTO> getAlunos() {
        return alunoMapper.toDTOList(alunoRepository.findAll());
    }

    // 🔹 Cadastrar um professor
    public ProfessorDTO cadastrarProfessor(Professor professor) {
        return professorMapper.toDTO(professorRepository.save(professor));
    }

    // 🔹 Listar todos os professores
    public List<ProfessorDTO> getProfessores() {
        return professorMapper.toDTOList(professorRepository.findAll());
    }

     // 🔹 Cadastrar um curso
    public CursoDTO cadastrarCurso(Curso curso) {
        return cursoMapper.toDTO(cursoRepository.save(curso));
    }

    // 🔹 Listar todos os cursos
    public List<CursoDTO> getCursos() {
        return cursoMapper.toDTOList(cursoRepository.findAll());
    }

    // 🔹 Cadastrar uma disciplina
    public DisciplinaDTO cadastrarDisciplina(Disciplina disciplina) {
        return disciplinaMapper.toDTO(disciplinaRepository.save(disciplina));
    }

    // 🔹 Listar todas as disciplinas
    public List<DisciplinaDTO> getDisciplinas() {
        return disciplinaMapper.toDTOList(disciplinaRepository.findAll());
    }

    // 🔹 Definir quais disciplinas serão oferecidas em um semestre
    public void definirDisciplinasOfertadas(List<Long> disciplinaIds) {
        List<Disciplina> disciplinas = disciplinaRepository.findAllById(disciplinaIds);
        disciplinas.forEach(disciplina -> disciplina.setStatus(com.sistema_matricula.sistema_matricula.Enum.Status.ATIVA));

        disciplinaRepository.saveAll(disciplinas);
    }

// 🔹 Cadastrar um usuário
public UsuarioDTO cadastrarUsuario(UsuarioCreate usuarioCreate) {
    return usuarioMapper.toDTO(usuarioRepository.save(usuarioMapper.toEntity(usuarioCreate)));
}
}   
