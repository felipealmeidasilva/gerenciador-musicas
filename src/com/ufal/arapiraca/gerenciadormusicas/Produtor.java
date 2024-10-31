package com.ufal.arapiraca.gerenciadormusicas;

import java.util.List;

public class Produtor extends Pessoa {
    private String nomeProfissional;

    public Produtor(String nome, int idade, String cpf, String nacionalidade, String senha, String nomeProfissional) {
        super(nome, idade, cpf, nacionalidade, senha);
        this.nomeProfissional = nomeProfissional;
    }

    public String getNomeProfissional() { return nomeProfissional; }
    
    public void publicarPodcast(List<Podcast> podcasts, String titulo, String descricao, int duracao, String apresentador) {
        Podcast novoPodcast = new Podcast(titulo, descricao, duracao, apresentador);
        podcasts.add(novoPodcast);
        System.out.println("Podcast '" + titulo + "' publicado com sucesso por " + nomeProfissional);
    }
}
