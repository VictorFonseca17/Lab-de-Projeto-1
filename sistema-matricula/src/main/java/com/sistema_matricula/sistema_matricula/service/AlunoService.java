package com.sistema_matricula.sistema_matricula.service;

import com.sistema_matricula.sistema_matricula.dto.DisciplinaDTO;
import com.sistema_matricula.sistema_matricula.entity.Aluno;
import com.sistema_matricula.sistema_matricula.entity.Disciplina;
import com.sistema_matricula.sistema_matricula.repository.AlunoRepository;
import com.sistema_matricula.sistema_matricula.repository.DisciplinaRepository;
import com.sistema_matricula.sistema_matricula.mapper.DisciplinaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private DisciplinaMapper disciplinaMapper;

    public void matricularEmDisciplinas(Long alunoId, List<Long> disciplinaIds) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        List<Disciplina> disciplinas = disciplinaRepository.findAllById(disciplinaIds);

        long countObrigatorias = disciplinas.stream()
                .filter(disciplina -> disciplina.getTipoDisciplina().equals(com.sistema_matricula.sistema_matricula.Enum.TipoDisciplina.OBRIGATORIA))
                .count();
        long countOptativas = disciplinas.stream()
                .filter(disciplina -> disciplina.getTipoDisciplina().equals(com.sistema_matricula.sistema_matricula.Enum.TipoDisciplina.OPTATIVA))
                .count();

        if (countObrigatorias > 4) {
            throw new RuntimeException("O aluno pode se matricular em no máximo 4 disciplinas obrigatórias.");
        }

        if (countOptativas > 2) {
            throw new RuntimeException("O aluno pode se matricular em no máximo 2 disciplinas optativas.");
        }

        aluno.getDisciplinas().addAll(disciplinas);
        alunoRepository.save(aluno);
    }

    // 🔹 Cancelar a matrícula em uma disciplina
    public void cancelarMatricula(Long alunoId, Long disciplinaId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        // Verifica se o aluno está matriculado nessa disciplina
        if (!aluno.getDisciplinas().contains(disciplina)) {
            throw new RuntimeException("O aluno não está matriculado nesta disciplina.");
        }

        // Cancela a matrícula removendo a disciplina
        aluno.getDisciplinas().remove(disciplina);
        alunoRepository.save(aluno);
    }

    // 🔹 Listar todas as disciplinas disponíveis para matrícula
    public List<DisciplinaDTO> listarDisciplinasDisponiveis() {
        List<Disciplina> disciplinas = disciplinaRepository.findAllByStatus("ATIVA");

        return disciplinas.stream()
                .map(disciplinaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
