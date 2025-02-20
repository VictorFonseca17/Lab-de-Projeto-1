import java.util.List;

public class Professor {

    private int id;
    private String nome;
    private String senha;
    private List<Disciplina> disciplinas; // Lista de disciplinas lecionadas pelo professor

    public String getNome() {
        return this.nome;
    }


    public List<Aluno> consultarAlunos(Disciplina disciplina) {
        // Retorna a lista de alunos matriculados em uma disciplina
        return disciplina.getAlunosMatriculados();
    }
}