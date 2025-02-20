import java.util.List;

public class Secretaria {
    
    private int id;
    private String nome;
    private String senha;

    public void gerarCurriculo() {
        // Gera o currículo do semestre
        System.out.println("Currículo gerado com sucesso.");
    }

    public void cadastrarProfessor(Professor professor) {
        // Cadastra um professor no sistema
        System.out.println("Professor cadastrado: " + professor.getNome());
    }

    public void cadastrarAluno(Aluno aluno) {
        // Cadastra um aluno no sistema
        System.out.println("Aluno cadastrado: " + aluno.getNome());
    }

    public void gerenciarDisciplinas() {
        // Gerencia as disciplinas (ativa/desativa)
        System.out.println("Disciplinas gerenciadas com sucesso.");
    }
}