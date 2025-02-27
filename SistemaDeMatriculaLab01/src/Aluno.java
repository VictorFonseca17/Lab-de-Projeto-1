import java.io.*;
import java.util.*;

public class Aluno {
    private int id;
    private String nome;
    private String senha;
    private List<Integer> matriculas; // Aqui você está armazenando apenas IDs de disciplinas

    // Construtor
    public Aluno(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.matriculas = new ArrayList<>();
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

    public List<Integer> getMatriculas() {
        return matriculas;
    }

    // Métodos de matrícula
    public void matricular(int disciplinaId) {
        if (!matriculas.contains(disciplinaId)) {
            matriculas.add(disciplinaId);
        }
    }

    public void cancelarMatricula(int disciplinaId) {
        matriculas.remove(Integer.valueOf(disciplinaId));
    }

    // Exibir disciplinas matriculadas
    public void exibirDisciplinasMatriculadas(List<Disciplina> disciplinas) {
        if (matriculas.isEmpty()) {
            System.out.println("Você ainda não está matriculado em nenhuma disciplina.");
        } else {
            System.out.println("Disciplinas Matriculadas:");
            for (int disciplinaId : matriculas) {
                for (Disciplina disciplina : disciplinas) {
                    if (disciplina.getId() == disciplinaId) {
                        System.out.println(disciplina.getNome());
                        break;
                    }
                }
            }
        }
    }
    
    // Salvar o objeto Aluno em um arquivo .txt
    public static void salvarAluno(Aluno aluno) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("aluno_" + aluno.getId() + ".txt"))) {
            writer.write("ID: " + aluno.getId() + "\n");
            writer.write("Nome: " + aluno.getNome() + "\n");
            writer.write("Senha: " + aluno.getSenha() + "\n");
            writer.write("Matriculas: " + aluno.getMatriculas() + "\n"); // Salva as matrículas como uma lista
            System.out.println("Aluno salvo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carregar o objeto Aluno a partir de um arquivo .txt
    public static Aluno carregarAluno(int alunoId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("aluno_" + alunoId + ".txt"))) {
            String linha;
            String nome = "";
            String senha = "";
            List<Integer> matriculas = new ArrayList<>();
            int id = alunoId;
            
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Nome:")) {
                    nome = linha.substring(6).trim(); // Remove o prefixo "Nome: "
                }
                if (linha.startsWith("Senha:")) {
                    senha = linha.substring(7).trim(); // Remove o prefixo "Senha: "
                }
                if (linha.startsWith("Matriculas:")) {
                    // Extrai os valores das matriculas
                    String matriculasStr = linha.substring(11).trim(); // Remove o prefixo "Matriculas: "
                    if (!matriculasStr.isEmpty()) {
                        String[] matriculasArray = matriculasStr.replace("[", "").replace("]", "").split(",");
                        for (String matricula : matriculasArray) {
                            matriculas.add(Integer.parseInt(matricula.trim()));
                        }
                    }
                }
            }
            return new Aluno(id, nome, senha);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
