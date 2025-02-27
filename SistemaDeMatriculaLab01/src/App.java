import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Aluno> alunos = new ArrayList<>();
        List<Professor> professores = new ArrayList<>();
        List<Disciplina> disciplinas = new ArrayList<>();
        List<Secretaria> secretarias = new ArrayList<>();
        List<Curso> cursos = new ArrayList<>();

        alunos.add(new Aluno(1, "João", "1234"));
        professores.add(new Professor(1, "Carlos", "prof123"));
        secretarias.add(new Secretaria(1, "Ana", "admin"));

        System.out.println("--- Sistema de Login ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Secretaria secretaria : secretarias) {
            if (secretaria.getNome().equals(nome) && secretaria.getSenha().equals(senha)) {
                System.out.println("Login bem-sucedido! Bem-vindo, Secretaria " + secretaria.getNome());
                exibirMenuSecretaria(scanner, alunos, professores, disciplinas, secretaria, cursos);
                return;
            }
        }
        for (Professor professor : professores) {
            if (professor.getNome().equals(nome) && professor.getSenha().equals(senha)) {
                System.out.println("Login bem-sucedido! Bem-vindo, Professor " + professor.getNome());
                exibirMenuProfessor(scanner, professor, disciplinas);
                return;
            }
        }
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equals(nome) && aluno.getSenha().equals(senha)) {
                System.out.println("Login bem-sucedido! Bem-vindo, Aluno " + aluno.getNome());
                return;
            }
        }

        System.out.println("Login falhou. Nome ou senha incorretos.");
        scanner.close();
    }

    public static void exibirMenuSecretaria(Scanner scanner, List<Aluno> alunos, List<Professor> professores, List<Disciplina> disciplinas, Secretaria secretaria, List<Curso> cursos) {
    int opcao;
        do {
            System.out.println("\n--- Menu Secretaria ---");
            System.out.println("1. Gerenciar Alunos");
            System.out.println("2. Gerenciar Professores");
            System.out.println("3. Gerenciar Disciplinas");
            System.out.println("4. Gerar currículo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    gerenciarAlunos(scanner, secretaria, alunos);

                    break;
                case 2:
                    gerenciarProfessores(scanner, secretaria, professores);
                    break;
                case 3:
                    gerenciarDisciplinas(scanner, secretaria, disciplinas);
                    break;
                case 4:
                    gerenciarCurriculo(scanner, secretaria, disciplinas, cursos, alunos);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    public static void gerenciarAlunos(Scanner scanner, Secretaria secretaria, List<Aluno> alunos){
    int opcao;
        do {
            System.out.println("\n--- Gerenciamento de Alunos ---");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Listar Alunos");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                secretaria.cadastrarAluno(alunos);
                break;
                case 2:
                secretaria.listarAlunos(alunos);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    public static void gerenciarProfessores(Scanner scanner, Secretaria secretaria, List<Professor> professores) {
        int opcao;
        do {
            System.out.println("\n--- Gerenciamento de Professores ---");
            System.out.println("1. Cadastrar Professor");
            System.out.println("2. Listar Professores");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
    
            switch (opcao) {
                case 1:
                secretaria.cadastrarProfessor(scanner, professores);
                break;
                case 2:
                secretaria.listarProfessores(professores);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    public static void gerenciarDisciplinas(Scanner scanner, Secretaria secretaria, List<Disciplina> disciplinas) {
        int opcao;
        do {
            System.out.println("\n--- Gerenciamento de Disciplinas ---");
            System.out.println("1. Listar Disciplinas");
            System.out.println("2. Cadastrar Disciplina");
            System.out.println("3. Remover Disciplina");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    secretaria.listarDisciplinas(disciplinas);
                    break;
                case 2:
                    secretaria.cadastrarDisciplina(disciplinas);
                    break;
                case 3:
                    secretaria.removerDisciplina(disciplinas);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
    public static void gerenciarCurriculo(Scanner scanner, Secretaria secretaria, List<Disciplina> disciplinas, List<Curso> cursos, List<Aluno> alunos) {
        System.out.println("\n--- Gerar Currículo ---");
            secretaria.gerarCurriculo(alunos, disciplinas, cursos);
    }
    
    public static void gerarCurriculoAluno(Aluno aluno, List<Disciplina> disciplinas, List<Curso> cursos) {
        System.out.println("\n--- Currículo ---");
        aluno.exibirDisciplinasMatriculadas(disciplinas);  // Exibe as disciplinas matriculadas
        
        // Aqui, podemos adicionar mais informações sobre o aluno, como os cursos das disciplinas
        for (int disciplinaId : aluno.getMatriculas()) {
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

    public static void exibirMenuAluno(Scanner scanner,List<Disciplina> disciplinas, Aluno aluno, List<Curso> cursos) {
        int opcao;
        do {
            System.out.println("\n--- Menu do Aluno ---");
            System.out.println("1. Ver Disciplinas Matriculadas");
            System.out.println("2. Matricular-se em uma Disciplina");
            System.out.println("3. Cancelar Matrícula em uma Disciplina");
            System.out.println("4. Ver Currículo");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    aluno.exibirDisciplinasMatriculadas(disciplinas);
                    break;
                case 2:
                    matricularEmDisciplina(scanner, aluno, disciplinas);
                    break;
                case 3:
                    cancelarMatricula(scanner, aluno);
                    break;
                case 4:
                    gerarCurriculoAluno(aluno, disciplinas, cursos);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 5);
    }
    
    public static void matricularEmDisciplina(Scanner scanner, Aluno aluno, List<Disciplina> disciplinas) {
        System.out.println("\n--- Matricular em Disciplina ---");
        System.out.print("Digite o ID da disciplina para matricular: ");
        int disciplinaId = scanner.nextInt();
        
        boolean disciplinaEncontrada = false;
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getId() == disciplinaId) {
                aluno.matricular(disciplinaId);
                System.out.println("Você foi matriculado na disciplina " + disciplina.getNome());
                disciplinaEncontrada = true;
                break;
            }
        }

        if (!disciplinaEncontrada) {
            System.out.println("Disciplina não encontrada.");
        }
    }
    public static void cancelarMatricula(Scanner scanner, Aluno aluno) {
        System.out.println("\n--- Cancelar Matrícula ---");
        System.out.print("Digite o ID da disciplina para cancelar a matrícula: ");
        int disciplinaId = scanner.nextInt();

        aluno.cancelarMatricula(disciplinaId);
        System.out.println("Matrícula cancelada com sucesso.");
    }

    public static void exibirMenuProfessor(Scanner scanner, Professor professor, List<Disciplina> todasDisciplinas) {
        int opcao;
        do {
            System.out.println("\n--- Menu Professor ---");
            System.out.println("1. Consultar Alunos Matriculados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    professor.consultarAlunos(todasDisciplinas);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }
}