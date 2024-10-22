package com.ufal.arapiraca.gerenciadormusicas;


class Pessoa {
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

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }

}

