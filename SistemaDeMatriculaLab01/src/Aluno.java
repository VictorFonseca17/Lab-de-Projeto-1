import java.io.*;
import java.util.*;

public class Aluno implements Serializable {
    private int id;
    private String nome;
    private String senha;
    private List<Integer> matriculas;

    public Aluno(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.matriculas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public List<Integer> getMatriculas() {
        return matriculas;
    }

    public void matricular(int disciplinaId) {
        if (!matriculas.contains(disciplinaId)) {
            matriculas.add(disciplinaId);
        }
    }

    public void cancelarMatricula(int disciplinaId) {
        matriculas.remove(Integer.valueOf(disciplinaId));
    }

    // Método para exibir as disciplinas matriculadas (usando o ID das disciplinas)
    public void exibirDisciplinasMatriculadas(List<Disciplina> disciplinas) {
        if (matriculas.isEmpty()) {
            System.out.println("Você ainda não está matriculado em nenhuma disciplina.");
        } else {
            System.out.println("Disciplinas Matriculadas:");
            for (int disciplinaId : matriculas) {
                for (Disciplina disciplina : disciplinas) {
                    if (disciplina.getId() == disciplinaId) {
                        System.out.println(disciplina.getNome());
                        break;
                    }
                }
            }
        }
    }
}
