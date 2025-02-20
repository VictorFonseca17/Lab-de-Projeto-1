public class App {
    public static void main(String[] args) {

        Secretaria secretaria = new Secretaria(1, "Ana Souza", "admin123");

        Professor professor1 = new Professor(1, "Carlos Silva", "prof123");
        Professor professor2 = new Professor(2, "Mariana Rocha", "prof456");

        secretaria.cadastrarProfessor(professor1);
        secretaria.cadastrarProfessor(professor2);

        Aluno aluno1 = new Aluno(1, "Lucas Mendes", "senha123");
        Aluno aluno2 = new Aluno(2, "Fernanda Lima", "senha456");
        Aluno aluno3 = new Aluno(3, "Pedro Oliveira", "senha789");

        secretaria.cadastrarAluno(aluno1);
        secretaria.cadastrarAluno(aluno2);
        secretaria.cadastrarAluno(aluno3);

        Disciplina disciplina1 = new Disciplina(1, "Matemática", 4, professor1);
        Disciplina disciplina2 = new Disciplina(2, "História", 3, professor2);

        professor1.adicionarDisciplina(disciplina1);
        professor2.adicionarDisciplina(disciplina2);

        Curso curso = new Curso(1, "Engenharia de Software", 40);

        curso.adicionarDisciplina(disciplina1);
        curso.adicionarDisciplina(disciplina2);

        aluno1.matricular(disciplina1);
        aluno2.matricular(disciplina1);
        aluno3.matricular(disciplina1);

        aluno1.matricular(disciplina2);
        aluno2.matricular(disciplina2);

        System.out.println("A disciplina " + disciplina1.getNome() + " está ativa? " + disciplina1.verificarAtiva());
        System.out.println("A disciplina " + disciplina2.getNome() + " está ativa? " + disciplina2.verificarAtiva());

        secretaria.adicionarDisciplina(disciplina1);
        secretaria.adicionarDisciplina(disciplina2);

        secretaria.gerenciarDisciplinas(disciplina1, true);
        secretaria.gerenciarDisciplinas(disciplina2, false);

        System.out.println("Alunos matriculados em " + disciplina1.getNome() + ":");
        for (Aluno aluno : professor1.consultarAlunos(disciplina1)) {
            System.out.println("- " + aluno.getNome());
        }

        aluno3.cancelarMatricula(disciplina1);
        System.out.println("Aluno " + aluno3.getNome() + " cancelou a matrícula em " + disciplina1.getNome());

        System.out.println("A disciplina " + disciplina1.getNome() + " está ativa após cancelamento? " + disciplina1.verificarAtiva());
    }
}
