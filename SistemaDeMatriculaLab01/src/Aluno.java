import java.util.ArrayList;
import java.util.List;

public class Aluno {

    private int id;
    private String nome;
    private String senha;
    private List<Disciplina> matriculas; // Lista de disciplinas em que o aluno está matriculado

    public String getNome() {
        return this.nome;
    }

    public void matricular(Disciplina disciplina) {
        // Matricula o aluno em uma disciplina
        this.matriculas.add(disciplina);
        disciplina.adicionarAluno(this); // Adiciona o aluno à lista de matriculados da disciplina
    }

    public void cancelarMatricula(Disciplina disciplina) {
        // Cancela a matrícula do aluno em uma disciplina
        this.matriculas.remove(disciplina);
        disciplina.removerAluno(this); // Remove o aluno da lista de matriculados da disciplina
    }
}