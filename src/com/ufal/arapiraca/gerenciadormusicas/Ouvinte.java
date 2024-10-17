package com.ufal.arapiraca.gerenciadormusicas;

// Classe específica para o ouvinte
class Ouvinte extends Usuario {
    private String nomeExibicao; // Nome exibido para outros ouvintes

    // Construtor
    public Ouvinte(String nome, int idade, String cpf, String nacionalidade, String senha, String nomeExibicao) {
        super(nome, idade, cpf, nacionalidade, senha);
        this.nomeExibicao = nomeExibicao;
    }

    // Getters e Setters
    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    // Método para verificar a idade
    public boolean calcularMenorIdade(int idade) {
        // Lógica de verificacao
        if (idade < 18) {
            return true;
        } else {
            return false;
        }
    }
}