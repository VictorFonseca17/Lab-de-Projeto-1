import java.util.List;

public class Curso {
    
    private int id;
    private String nome;
    private int creditosTotais;
    private List<Disciplina> disciplinas; // Lista de disciplinas que compõem o curso

    public void adicionarDisciplina(Disciplina disciplina) {
        // Adiciona uma disciplina ao curso
        this.disciplinas.add(disciplina);
    }
}