import java.io.*;
import java.util.*;

public class Disciplina {
    private int id;
    private String nome;
    private int creditos;
    private int cursoId; // ID do curso
    private List<Integer> alunosMatriculados; // IDs dos alunos

    // Construtor
    public Disciplina(int id, String nome, int creditos, int cursoId) {
        this.id = id;
        this.nome = nome;
        this.creditos = creditos;
        this.cursoId = cursoId;
        this.alunosMatriculados = new ArrayList<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getCursoId() {
        return cursoId;
    }

    public List<Integer> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    // Adicionar um aluno à disciplina
    public void adicionarAluno(int alunoId) {
        if (!alunosMatriculados.contains(alunoId)) {
            alunosMatriculados.add(alunoId);
        }
    }

    // Remover um aluno da disciplina
    public void removerAluno(int alunoId) {
        alunosMatriculados.remove(Integer.valueOf(alunoId));
    }

    // Salvar o objeto Disciplina em um arquivo .txt
    public static void salvarDisciplina(Disciplina disciplina) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("disciplina_" + disciplina.getId() + ".txt"))) {
            writer.write("ID: " + disciplina.getId() + "\n");
            writer.write("Nome: " + disciplina.getNome() + "\n");
            writer.write("Creditos: " + disciplina.getCreditos() + "\n");
            writer.write("Curso ID: " + disciplina.getCursoId() + "\n");
            writer.write("Alunos Matriculados: " + disciplina.getAlunosMatriculados() + "\n"); // Lista de alunos matriculados
            System.out.println("Disciplina salva com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carregar o objeto Disciplina a partir de um arquivo .txt
    public static Disciplina carregarDisciplina(int disciplinaId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("disciplina_" + disciplinaId + ".txt"))) {
            String linha;
            String nome = "";
            int creditos = 0;
            int cursoId = 0;
            List<Integer> alunosMatriculados = new ArrayList<>();
            int id = disciplinaId;
            
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Nome:")) {
                    nome = linha.substring(6).trim(); // Remove o prefixo "Nome: "
                }
                if (linha.startsWith("Creditos:")) {
                    creditos = Integer.parseInt(linha.substring(9).trim()); // Remove o prefixo "Creditos: "
                }
                if (linha.startsWith("Curso ID:")) {
                    cursoId = Integer.parseInt(linha.substring(9).trim()); // Remove o prefixo "Curso ID: "
                }
                if (linha.startsWith("Alunos Matriculados:")) {
                    // Extrai os valores dos alunos matriculados
                    String alunosStr = linha.substring(21).trim(); // Remove o prefixo "Alunos Matriculados: "
                    if (!alunosStr.isEmpty()) {
                        String[] alunosArray = alunosStr.replace("[", "").replace("]", "").split(",");
                        for (String aluno : alunosArray) {
                            alunosMatriculados.add(Integer.parseInt(aluno.trim()));
                        }
                    }
                }
            }
            return new Disciplina(id, nome, creditos, cursoId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
