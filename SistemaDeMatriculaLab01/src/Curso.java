import java.io.*;
import java.util.*;

public class Curso implements Serializable {
    private int id;
    private String nome;
    private int creditosTotais;
    private List<Integer> disciplinas; // IDs das disciplinas

    public Curso(int id, String nome, int creditosTotais) {
        this.id = id;
        this.nome = nome;
        this.creditosTotais = creditosTotais;
        this.disciplinas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarDisciplina(int disciplinaId) {
        if (!disciplinas.contains(disciplinaId)) {
            disciplinas.add(disciplinaId);
        }
    }
}
