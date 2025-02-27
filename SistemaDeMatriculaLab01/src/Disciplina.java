import java.io.*;
import java.util.*;

public class Disciplina implements Serializable {
    private int id;
    private String nome;
    private int creditos;
    private int cursoId; // ID do curso
    private List<Integer> alunosMatriculados; // IDs dos alunos

    public Disciplina(int id, String nome, int creditos, int cursoId) {
        this.id = id;
        this.nome = nome;
        this.creditos = creditos;
        this.cursoId = cursoId;
        this.alunosMatriculados = new ArrayList<>();
    }

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

    public void adicionarAluno(int alunoId) {
        if (!alunosMatriculados.contains(alunoId)) {
            alunosMatriculados.add(alunoId);
        }
    }

    public void removerAluno(int alunoId) {
        alunosMatriculados.remove(Integer.valueOf(alunoId));
    }
    public List<Integer> getAlunosMatriculados() {
        return alunosMatriculados;
    }
}