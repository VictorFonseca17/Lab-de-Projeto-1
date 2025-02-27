import java.io.*;
import java.util.*;

public class Secretaria {
    private int id;
    private String nome;
    private String senha;

    public Secretaria(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    // Método genérico para listar qualquer tipo de objeto com um nome e lista
    private <T> void listar(String nomeEntidade, List<T> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhum " + nomeEntidade + " cadastrado.");
        } else {
            System.out.println("\nLista de " + nomeEntidade + "s:");
            for (T item : lista) {
                System.out.println(item);
            }
        }
    }

    // Método para cadastrar professor
    public void cadastrarProfessor(Scanner scanner, List<Professor> professores) {
        System.out.print("Digite o nome do professor: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a senha do professor: ");
        String senha = scanner.nextLine();
    
        int id = professores.size() + 1; // Gerando um ID simples baseado no tamanho da lista
        Professor novoProfessor = new Professor(id, nome, senha);
        professores.add(novoProfessor);
    
        System.out.println("Professor cadastrado com sucesso!");
    }

    // Método para listar professores
    public void listarProfessores(List<Professor> professores) {
        listar("Professor", professores);
    }

    // Método para cadastrar aluno
    public void cadastrarAluno(Scanner scanner, List<Aluno> alunos) {
        System.out.print("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a senha do aluno: ");
        String senha = scanner.nextLine();
    
        int id = alunos.size() + 1; // Gerando um ID simples baseado no tamanho da lista
        Aluno novoAluno = new Aluno(id, nome, senha);
        alunos.add(novoAluno);
    
        System.out.println("Aluno cadastrado com sucesso!");
    }

    // Método para listar alunos
    public void listarAlunos(List<Aluno> alunos) {
        listar("Aluno", alunos);
    }

    // Método para cadastrar disciplina
    public void cadastrarDisciplina(Scanner scanner, List<Disciplina> disciplinas) {
        System.out.print("Digite o nome da disciplina: ");
        String nome = scanner.nextLine();
        System.out.print("Digite os créditos da disciplina: ");
        int creditos = scanner.nextInt();
        System.out.print("Digite o ID do curso associado: ");
        int cursoId = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        
        int id = disciplinas.size() + 1; // Gerando um ID simples baseado no tamanho da lista
        Disciplina novaDisciplina = new Disciplina(id, nome, creditos, cursoId);
        disciplinas.add(novaDisciplina);
        
        System.out.println("Disciplina cadastrada com sucesso!");
    }

    // Método para listar disciplinas
    public void listarDisciplinas(List<Disciplina> disciplinas) {
        listar("Disciplina", disciplinas);
    }

    // Método para remover disciplina
    public void removerDisciplina(Scanner scanner, List<Disciplina> disciplinas) {
        System.out.print("Digite o ID da disciplina a ser removida: ");
        int id = scanner.nextInt();
        
        for (Iterator<Disciplina> iterator = disciplinas.iterator(); iterator.hasNext();) {
            Disciplina disciplina = iterator.next();
            if (disciplina.getId() == id) {
                iterator.remove();
                System.out.println("Disciplina removida com sucesso!");
                return;
            }
        }
        System.out.println("Disciplina não encontrada.");
    }

    // Método para gerar o currículo de um aluno
    public void gerarCurriculo(Scanner scanner, List<Aluno> alunos, List<Disciplina> disciplinas, List<Curso> cursos) {
        System.out.print("Digite o ID do aluno para gerar o currículo: ");
        int idAluno = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        
        // Buscar aluno pelo ID
        Aluno alunoEncontrado = null;
        for (Aluno aluno : alunos) {
            if (aluno.getId() == idAluno) {
                alunoEncontrado = aluno;
                break;
            }
        }
    
        if (alunoEncontrado == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }
    
        // Gerar o currículo do aluno
        System.out.println("Currículo do aluno " + alunoEncontrado.getNome());
        for (int disciplinaId : alunoEncontrado.getMatriculas()) {
            for (Disciplina disciplina : disciplinas) {
                if (disciplina.getId() == disciplinaId) {
                    String cursoNome = "Desconhecido";
                    for (Curso curso : cursos) {
                        if (curso.getId() == disciplina.getCursoId()) {
                            cursoNome = curso.getNome();
                            break;
                        }
                    }
                    System.out.println("Disciplina: " + disciplina.getNome() + ", Curso: " + cursoNome + ", Créditos: " + disciplina.getCreditos());
                }
            }
        }
    }

    // Método para salvar os dados em um arquivo
    public void salvarDados(String caminho, List<Professor> professores, List<Aluno> alunos, List<Disciplina> disciplinas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            // Salvar professores
            writer.write("Professores:\n");
            for (Professor professor : professores) {
                writer.write(professor.getId() + "," + professor.getNome() + "," + professor.getSenha() + "\n");
            }

            // Salvar alunos
            writer.write("\nAlunos:\n");
            for (Aluno aluno : alunos) {
                writer.write(aluno.getId() + "," + aluno.getNome() + "," + aluno.getSenha() + "\n");
            }

            // Salvar disciplinas
            writer.write("\nDisciplinas:\n");
            for (Disciplina disciplina : disciplinas) {
                writer.write(disciplina.getId() + "," + disciplina.getNome() + "," + disciplina.getCreditos() + "," + disciplina.getCursoId() + "\n");
            }

            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    // Método para carregar os dados de um arquivo
    public void carregarDados(String caminho, List<Professor> professores, List<Aluno> alunos, List<Disciplina> disciplinas) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            List<String> dados = new ArrayList<>();

            while ((linha = reader.readLine()) != null) {
                dados.add(linha);
            }

            // Carregar professores
            int index = dados.indexOf("Professores:") + 1;
            while (index < dados.size() && !dados.get(index).equals("Alunos:")) {
                String[] partes = dados.get(index).split(",");
                Professor professor = new Professor(Integer.parseInt(partes[0]), partes[1], partes[2]);
                professores.add(professor);
                index++;
            }

            // Carregar alunos
            index++;
            while (index < dados.size() && !dados.get(index).equals("Disciplinas:")) {
                String[] partes = dados.get(index).split(",");
                Aluno aluno = new Aluno(Integer.parseInt(partes[0]), partes[1], partes[2]);
                alunos.add(aluno);
                index++;
            }

            // Carregar disciplinas
            index++;
            while (index < dados.size()) {
                String[] partes = dados.get(index).split(",");
                Disciplina disciplina = new Disciplina(Integer.parseInt(partes[0]), partes[1], Integer.parseInt(partes[2]), Integer.parseInt(partes[3]));
                disciplinas.add(disciplina);
                index++;
            }

            System.out.println("Dados carregados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }
}
