package dio.banco.entities;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private static int SEQUENCIAL = 100;

    private String nome;
    private int codigo;

    private List<Conta> contas;

    public Banco() {
    	contas = new ArrayList<>();
        codigo = SEQUENCIAL++;
    }

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
        codigo = SEQUENCIAL++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public List<Conta> getContas() {
        return contas;
    }

}
