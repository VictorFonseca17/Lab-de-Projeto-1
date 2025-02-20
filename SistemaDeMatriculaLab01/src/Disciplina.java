import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    
    private int id;
    private String nome;
    private int creditos;
    private List<Aluno> alunosMatriculados; 
    private Professor professor; 
    private boolean ativa; 

    public Disciplina(int id, String nome, int creditos, Professor professor) {
        this.id = id;
        this.nome = nome;
        this.creditos = creditos;
        this.professor = professor;
        this.alunosMatriculados = new ArrayList<>();
        this.ativa = false; 
    }

    public void adicionarAluno(Aluno aluno) {
        if (!alunosMatriculados.contains(aluno)) {
            this.alunosMatriculados.add(aluno);
            atualizarStatus();
        }
    }

    public void removerAluno(Aluno aluno) {
        this.alunosMatriculados.remove(aluno);
        atualizarStatus();
    }

    public boolean verificarAtiva() {
        return ativa;
    }

    private void atualizarStatus() {
        this.ativa = this.alunosMatriculados.size() >= 3;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    public Professor getProfessor() {
        return professor;
    }

    public boolean isAtiva() {
        return ativa;
    }
}
