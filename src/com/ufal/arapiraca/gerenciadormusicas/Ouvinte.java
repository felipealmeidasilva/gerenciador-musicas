package com.ufal.arapiraca.gerenciadormusicas;

class Ouvinte extends Pessoa {
    private String nomeExibicao; // Nome exibido para outros ouvintes


    public Ouvinte(String nome, int idade, String cpf, String nacionalidade, String senha, String nomeExibicao) {
        super(nome, idade, cpf, nacionalidade, senha);
        this.nomeExibicao = nomeExibicao;
    }


    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }


    public boolean calcularMenorIdade(int idade) {
        if (idade < 18) {
            return true;
        } else {
            return false;
        }
    }
}