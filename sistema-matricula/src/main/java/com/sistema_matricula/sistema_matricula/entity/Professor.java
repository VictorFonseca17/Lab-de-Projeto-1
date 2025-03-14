package com.sistema_matricula.sistema_matricula.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Professor extends Usuario {

    @Schema(description = "Nome do professor", example = "João da Silva")
    // Não é necessário declarar novamente o campo 'nome', pois ele já vem da classe Usuario

    // Relacionamento de muitos-para-muitos com a entidade Disciplina
    @ManyToMany
    @JoinTable(
        name = "professor_disciplina",
        joinColumns = @JoinColumn(name = "professor_id"),
        inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private Set<Disciplina> disciplinas = new HashSet<>();

    // 🔹 Método para adicionar uma disciplina ao professor
    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    // 🔹 Método para remover uma disciplina do professor
    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }
}
