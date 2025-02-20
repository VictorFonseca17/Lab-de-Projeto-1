import java.util.ArrayList;
import java.util.List;

public class Aluno {

    private int id;
    private String nome;
    private String senha;
    private List<Disciplina> matriculas; 

    public Aluno(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.matriculas = new ArrayList<>(); 
    }

    public void matricular(Disciplina disciplina) {
        if (!matriculas.contains(disciplina)) {
            this.matriculas.add(disciplina);
            disciplina.adicionarAluno(this); 
        }
    }

    public void cancelarMatricula(Disciplina disciplina) {
        if (matriculas.contains(disciplina)) {
            this.matriculas.remove(disciplina);
            disciplina.removerAluno(this); 
        }
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

    public List<Disciplina> getMatriculas() {
        return matriculas;
    }
}
