import java.io.*;
import java.util.*;

public class Professor implements Serializable {
    private int id;
    private String nome;
    private String senha;
    private List<Integer> disciplinas; // IDs das disciplinas que o professor leciona

    public Professor(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.disciplinas = new ArrayList<>();
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

    public List<Integer> getDisciplinas() {
        return disciplinas;
    }

    // Consultar alunos matriculados em uma disciplina que o professor leciona
    public void consultarAlunos(List<Disciplina> todasDisciplinas) {
        if (disciplinas.isEmpty()) {
            System.out.println("Você não leciona nenhuma disciplina.");
            return;
        }

        // Exibe as disciplinas que o professor leciona
        System.out.println("\nDisciplinas que você leciona:");
        for (int i = 0; i < disciplinas.size(); i++) {
            for (Disciplina disciplina : todasDisciplinas) {
                if (disciplina.getId() == disciplinas.get(i)) {
                    System.out.println(i + 1 + ". " + disciplina.getNome());
                    break;
                }
            }
        }

        // Solicita ao professor escolher uma disciplina para consultar os alunos
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEscolha uma disciplina para ver os alunos matriculados (1-" + disciplinas.size() + "): ");
        int opcao = scanner.nextInt();

        if (opcao < 1 || opcao > disciplinas.size()) {
            System.out.println("Opção inválida.");
            return;
        }

        // Obter a disciplina selecionada
        Disciplina disciplinaSelecionada = null;
        for (Disciplina disciplina : todasDisciplinas) {
            if (disciplina.getId() == disciplinas.get(opcao - 1)) {
                disciplinaSelecionada = disciplina;
                break;
            }
        }

        // Exibe os alunos matriculados na disciplina
        List<Integer> alunosMatriculados = disciplinaSelecionada.getAlunosMatriculados();
        if (alunosMatriculados.isEmpty()) {
            System.out.println("Não há alunos matriculados nessa disciplina.");
        } else {
            System.out.println("\nAlunos matriculados na disciplina " + disciplinaSelecionada.getNome() + ":");
            for (Integer alunoId : alunosMatriculados) {
                System.out.println("Aluno ID: " + alunoId);
            }
        }
    }

}
