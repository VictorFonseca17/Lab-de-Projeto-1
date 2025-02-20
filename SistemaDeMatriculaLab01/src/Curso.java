import java.util.ArrayList;
import java.util.List;

public class Curso {
    
    private int id;
    private String nome;
    private int creditosTotais;
    private List<Disciplina> disciplinas; 

    public Curso(int id, String nome, int creditosTotais) {
        this.id = id;
        this.nome = nome;
        this.creditosTotais = creditosTotais;
        this.disciplinas = new ArrayList<>(); 
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (!disciplinas.contains(disciplina)) {
            this.disciplinas.add(disciplina);
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCreditosTotais() {
        return creditosTotais;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
}
