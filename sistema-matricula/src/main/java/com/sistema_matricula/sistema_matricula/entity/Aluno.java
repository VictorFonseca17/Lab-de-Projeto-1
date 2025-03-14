package com.sistema_matricula.sistema_matricula.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Aluno extends Usuario {

    @Schema(description = "Nome do aluno", example = "João da Silva")

    @ManyToMany
    @JoinTable(
        name = "matricula",
        joinColumns = @JoinColumn(name = "aluno_id"),
        inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private Set<Disciplina> disciplinas = new HashSet<>();

    // 🔹 Método para adicionar uma disciplina ao aluno
    public void adicionarDisciplina(Disciplina disciplina) {
        if (disciplinas.size() < 6) {  // 4 obrigatórias + 2 optativas
            disciplinas.add(disciplina);
        } else {
            throw new RuntimeException("Limite de disciplinas excedido");
        }
    }

    // Método para remover uma disciplina do aluno
    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }
}
