package com.ufal.arapiraca.gerenciadormusicas;

public abstract class Pessoa {
    private String nome;
    private int idade;
    private String cpf;
    private String nacionalidade;
    private String senha;

    public Pessoa(String nome, int idade, String cpf, String nacionalidade, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.nacionalidade = nacionalidade;
        this.senha = senha;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getCpf() { return cpf; }
    public String getNacionalidade() { return nacionalidade; }
    public String getSenha() { return senha; }
}
