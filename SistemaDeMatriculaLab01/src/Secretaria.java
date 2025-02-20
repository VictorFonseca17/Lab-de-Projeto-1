import java.util.ArrayList;
import java.util.List;

public class Secretaria {
    
    private int id;
    private String nome;
    private String senha;
    private List<Professor> professores;
    private List<Aluno> alunos;
    private List<Disciplina> disciplinas;

    public Secretaria(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.professores = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
    }

    public void gerarCurriculo() {
        System.out.println("Currículo gerado com sucesso.");
    }

    // Método para cadastrar um professor
    public void cadastrarProfessor(Professor professor) {
        if (!professores.contains(professor)) {
            this.professores.add(professor);
            System.out.println("Professor cadastrado: " + professor.getNome());
        }
    }

    public void cadastrarAluno(Aluno aluno) {
        if (!alunos.contains(aluno)) {
            this.alunos.add(aluno);
            System.out.println("Aluno cadastrado: " + aluno.getNome());
        }
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        if (!disciplinas.contains(disciplina)) {
            this.disciplinas.add(disciplina);
        }
    }

    public void gerenciarDisciplinas(Disciplina disciplina, boolean ativar) {
        if (disciplinas.contains(disciplina)) {
            System.out.println("Disciplina " + disciplina.getNome() + 
                (ativar ? " ativada." : " desativada."));
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

    public List<Professor> getProfessores() {
        return professores;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
}
