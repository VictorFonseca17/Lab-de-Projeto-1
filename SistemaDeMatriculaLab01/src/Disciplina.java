import java.util.List;

public class Disciplina {
    
    private int id;
    private String nome;
    private List<Aluno> alunosMatriculados; // Lista de alunos matriculados na disciplina
    private Professor professor; // Professor que leciona a disciplina
    private boolean ativa; // Indica se a disciplina está ativa
    
    public void adicionarAluno(Aluno aluno) {
        // Adiciona um aluno à lista de matriculados
        this.alunosMatriculados.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        // Remove um aluno da lista de matriculados
        this.alunosMatriculados.remove(aluno);
    }

    public boolean verificarAtiva() {
        // Verifica se a disciplina está ativa (tem pelo menos 3 alunos matriculados)
        return this.alunosMatriculados.size() >= 3;
    }
}