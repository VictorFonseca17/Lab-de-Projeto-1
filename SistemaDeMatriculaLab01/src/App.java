import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Carregar dados do arquivo
        List<Aluno> alunos = carregarAlunos();
        List<Professor> professores = carregarProfessores();
        List<Disciplina> disciplinas = carregarDisciplinas();
        List<Secretaria> secretarias = carregarSecretarias();
        List<Curso> cursos = carregarCursos();

        System.out.println("--- Sistema de Login ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        secretarias.add(new Secretaria(1, "Ana", "admin"));

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
                exibirMenuAluno(scanner, disciplinas, aluno, cursos, alunos, secretarias.get(0));
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

    public static void gerenciarAlunos(Scanner scanner, Secretaria secretaria, List<Aluno> alunos) {
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
                    secretaria.cadastrarAluno(scanner, alunos);
                    salvarAlunos(alunos);  // Salvar alunos após a adição
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
                    salvarProfessores(professores);  // Salvar professores após a adição
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
                    secretaria.cadastrarDisciplina(scanner, disciplinas);
                    salvarDisciplinas(disciplinas);  // Salvar disciplinas após a adição
                    break;
                case 3:
                    secretaria.removerDisciplina(scanner, disciplinas);
                    salvarDisciplinas(disciplinas);  // Salvar disciplinas após a remoção
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
        secretaria.gerarCurriculo(scanner, alunos, disciplinas, cursos);
    }

    public static void exibirMenuAluno(Scanner scanner, List<Disciplina> disciplinas, Aluno aluno, List<Curso> cursos, List<Aluno> alunos, Secretaria secretaria) {
        int opcao;
        do {
            System.out.println("\n--- Menu do Aluno ---");
            System.out.println("1. Ver Disciplinas Matriculadas");
            System.out.println("2. Matricular-se em uma Disciplina");
            System.out.println("3. Cancelar Matrícula em uma Disciplina");
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

    public static List<Aluno> carregarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        File arquivo = new File("alunos.txt");
    
        // Verifica se o arquivo existe
        if (!arquivo.exists()) {
            System.out.println("Arquivo de alunos não encontrado. Criando um novo arquivo.");
            try {
                // Cria o arquivo caso ele não exista
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo de alunos: " + e.getMessage());
            }
        }
    
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";"); // Assumindo que os dados são separados por ponto e vírgula
                if (dados.length == 3) { // Exemplo de formato: nome, senha, id
                    Aluno aluno = new Aluno(Integer.parseInt(dados[0]), dados[1], dados[2]);
                    alunos.add(aluno);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public static List<Professor> carregarProfessores() {
        List<Professor> professores = null;
        File arquivo = new File("professores.txt");
    
        // Verifica se o arquivo existe
        if (!arquivo.exists()) {
            System.out.println("Arquivo de professores não encontrado. Criando um novo arquivo.");
            try {
                // Cria o arquivo caso ele não exista
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo de professores: " + e.getMessage());
            }
        }
    
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            professores = (List<Professor>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de professores não encontrado. Nenhum professor foi carregado.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar professores: " + e.getMessage());
        }
        return professores;
    }
    public static List<Disciplina> carregarDisciplinas() {
        List<Disciplina> disciplinas = null;
        File arquivo = new File("disciplinas.txt");
    
        // Verifica se o arquivo existe
        if (!arquivo.exists()) {
            System.out.println("Arquivo de disciplinas não encontrado. Criando um novo arquivo.");
            try {
                // Cria o arquivo caso ele não exista
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo de disciplinas: " + e.getMessage());
            }
        }
    
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            disciplinas = (List<Disciplina>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de disciplinas não encontrado. Nenhuma disciplina foi carregada.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar disciplinas: " + e.getMessage());
        }
        return disciplinas;
    }
    public static List<Curso> carregarCursos() {
        List<Curso> cursos = null;
        File arquivo = new File("cursos.txt");
    
        // Verifica se o arquivo existe
        if (!arquivo.exists()) {
            System.out.println("Arquivo de cursos não encontrado. Criando um novo arquivo.");
            try {
                // Cria o arquivo caso ele não exista
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo de cursos: " + e.getMessage());
            }
        }
    
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            cursos = (List<Curso>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de cursos não encontrado. Nenhum curso foi carregado.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar cursos: " + e.getMessage());
        }
        return cursos;
    }
    public static List<Secretaria> carregarSecretarias() {
        List<Secretaria> secretarias = new ArrayList<>();
        File arquivo = new File("secretarias.txt");

        // Verifica se o arquivo existe
        if (!arquivo.exists()) {
            System.out.println("Arquivo de secretarias não encontrado. Criando um novo arquivo.");
            try {
                // Cria o arquivo caso ele não exista
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao criar o arquivo de secretarias: " + e.getMessage());
            }
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                secretarias = (List<Secretaria>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erro ao carregar secretarias: " + e.getMessage());
            }
        }
        return secretarias;
    }

    public static void salvarAlunos(List<Aluno> alunos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("alunos.txt"))) {
            for (Aluno aluno : alunos) {
                writer.write(aluno.getId() + ";" + aluno.getNome() + ";" + aluno.getSenha()); // Exemplo de formato
                writer.newLine(); // Quebra de linha
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static void salvarProfessores(List<Professor> professores) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("professores.txt"))) {
        oos.writeObject(professores);
        System.out.println("Professores salvos com sucesso!");
    } catch (IOException e) {
        System.out.println("Erro ao salvar professores: " + e.getMessage());
    }
}

public static void salvarDisciplinas(List<Disciplina> disciplinas) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("disciplinas.txt"))) {
        oos.writeObject(disciplinas);
        System.out.println("Disciplinas salvas com sucesso!");
    } catch (IOException e) {
        System.out.println("Erro ao salvar disciplinas: " + e.getMessage());
    }
}
public static void salvarCursos(List<Curso> cursos) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cursos.txt"))) {
        oos.writeObject(cursos);
        System.out.println("Cursos salvos com sucesso!");
    } catch (IOException e) {
        System.out.println("Erro ao salvar cursos: " + e.getMessage());
    }
}

}
