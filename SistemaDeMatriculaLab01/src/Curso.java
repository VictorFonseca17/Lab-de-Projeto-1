import java.io.*;
import java.util.*;

public class Curso {
    private int id;
    private String nome;
    private int creditosTotais;
    private List<Integer> disciplinas; // IDs das disciplinas

    // Construtor
    public Curso(int id, String nome, int creditosTotais) {
        this.id = id;
        this.nome = nome;
        this.creditosTotais = creditosTotais;
        this.disciplinas = new ArrayList<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCreditosTotais() {
        return creditosTotais;
    }

    public List<Integer> getDisciplinas() {
        return disciplinas;
    }

    // Adicionar uma disciplina ao curso
    public void adicionarDisciplina(int disciplinaId) {
        if (!disciplinas.contains(disciplinaId)) {
            disciplinas.add(disciplinaId);
        }
    }

    // Salvar o objeto Curso em um arquivo .txt
    public static void salvarCurso(Curso curso) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("curso_" + curso.getId() + ".txt"))) {
            writer.write("ID: " + curso.getId() + "\n");
            writer.write("Nome: " + curso.getNome() + "\n");
            writer.write("Creditos Totais: " + curso.getCreditosTotais() + "\n");
            writer.write("Disciplinas: " + curso.getDisciplinas() + "\n"); // Salva as disciplinas como uma lista
            System.out.println("Curso salvo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carregar o objeto Curso a partir de um arquivo .txt
    public static Curso carregarCurso(int cursoId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("curso_" + cursoId + ".txt"))) {
            String linha;
            String nome = "";
            int creditosTotais = 0;
            List<Integer> disciplinas = new ArrayList<>();
            int id = cursoId;
            
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Nome:")) {
                    nome = linha.substring(6).trim(); // Remove o prefixo "Nome: "
                }
                if (linha.startsWith("Creditos Totais:")) {
                    creditosTotais = Integer.parseInt(linha.substring(17).trim()); // Remove o prefixo "Creditos Totais: "
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
            return new Curso(id, nome, creditosTotais);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
