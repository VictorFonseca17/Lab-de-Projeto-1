import java.io.*;
import java.util.*;

public class Professor {
    private int id;
    private String nome;
    private String senha;
    private List<Integer> disciplinas; // IDs das disciplinas que o professor leciona

    // Construtor
    public Professor(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.disciplinas = new ArrayList<>();
    }

    // Getters e setters
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

    @Override
    public String toString() {
        return id + ";" + nome + ";" + disciplinas;
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

    // Salvar o objeto Professor em um arquivo .txt
    public static void salvarProfessor(Professor professor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("professor_" + professor.getId() + ".txt"))) {
            writer.write("ID: " + professor.getId() + "\n");
            writer.write("Nome: " + professor.getNome() + "\n");
            writer.write("Senha: " + professor.getSenha() + "\n");
            writer.write("Disciplinas: " + professor.getDisciplinas() + "\n");
            System.out.println("Professor salvo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carregar o objeto Professor a partir de um arquivo .txt
    public static Professor carregarProfessor(int professorId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("professor_" + professorId + ".txt"))) {
            String linha;
            String nome = "";
            String senha = "";
            List<Integer> disciplinas = new ArrayList<>();
            int id = professorId;

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Nome:")) {
                    nome = linha.substring(6).trim(); // Remove o prefixo "Nome: "
                }
                if (linha.startsWith("Senha:")) {
                    senha = linha.substring(7).trim(); // Remove o prefixo "Senha: "
                }
                if (linha.startsWith("Disciplinas:")) {
                    // Extrai os valores das disciplinas
                    String disciplinasStr = linha.substring(12).trim(); // Remove o prefixo "Disciplinas: "
                    if (!disciplinasStr.isEmpty()) {
                        String[] disciplinasArray = disciplinasStr.replace("[", "").replace("]", "").split(",");
                        for (String disciplina : disciplinasArray) {
                            disciplinas.add(Integer.parseInt(disciplina.trim()));
                        }
                    }
                }
            }
            return new Professor(id, nome, senha);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
