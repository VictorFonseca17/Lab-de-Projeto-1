import java.util.ArrayList;
import java.util.List;

public class Professor {

    private int id;
    private String nome;
    private String senha;
    private List<Disciplina> disciplinas; 

    public Professor(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.disciplinas = new ArrayList<>(); 
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (!disciplinas.contains(disciplina)) {
            this.disciplinas.add(disciplina);
        }
    }

    public List<Aluno> consultarAlunos(Disciplina disciplina) {
        return disciplina.getAlunosMatriculados();
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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
}
