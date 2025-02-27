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

    public void listarProfessores(List<Professor> professores) {
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
        } else {
            System.out.println("\nLista de Professores:");
            for (Professor professor : professores) {
                System.out.println("ID: " + professor.getId() + " | Nome: " + professor.getNome());
            }
        }
    }

    
 public void cadastrarAluno(List<Aluno> alunos) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Digite o nome do aluno: ");
    String nome = scanner.nextLine();
    System.out.print("Digite a senha do aluno: ");
    String senha = scanner.nextLine();
    
    int id = alunos.size() + 1; // Gerando um ID simples baseado no tamanho da lista
    Aluno novoAluno = new Aluno(id, nome, senha);
    alunos.add(novoAluno);
    scanner.close();

    System.out.println("Aluno cadastrado com sucesso!");
}

    public void listarAlunos(List<Aluno> alunos) {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            System.out.println("\nLista de Alunos:");
            for (Aluno aluno : alunos) {
                System.out.println("ID: " + aluno.getId() + " | Nome: " + aluno.getNome());
            }
        }
    }

    public void cadastrarDisciplina(List<Disciplina> disciplinas) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome da disciplina: ");
        String nome = scanner.nextLine();
        System.out.print("Digite os créditos da disciplina: ");
        int creditos = scanner.nextInt();
        System.out.print("Digite o ID do curso associado: ");
        int cursoId = scanner.nextInt();
        
        int id = disciplinas.size() + 1; // Gerando um ID simples baseado no tamanho da lista
        Disciplina novaDisciplina = new Disciplina(id, nome, creditos, cursoId);
        disciplinas.add(novaDisciplina);
        System.out.println("Disciplina cadastrada com sucesso!");
        scanner.close();
    }

    // Método para listar disciplinas
    public void listarDisciplinas(List<Disciplina> disciplinas) {
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
        } else {
            System.out.println("\nLista de Disciplinas:");
            for (Disciplina disciplina : disciplinas) {
                System.out.println("ID: " + disciplina.getId() + " | Nome: " + disciplina.getNome() +
                        " | Créditos: " + disciplina.getCreditos() + " | Curso ID: " + disciplina.getCursoId());
            }
        }
    }

    // Método para remover disciplina
    public void removerDisciplina(List<Disciplina> disciplinas) {
        Scanner scanner = new Scanner(System.in);
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
        scanner.close();
    }

    public void gerarCurriculo(List<Aluno> alunos, List<Disciplina> disciplinas, List<Curso> cursos) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do aluno para gerar o currículo: ");
        int idAluno = scanner.nextInt();
        
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
        scanner.close();
    }
    
}
