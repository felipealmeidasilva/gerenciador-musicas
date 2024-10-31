package com.ufal.arapiraca.gerenciadormusicas;

public class Podcast extends Audio {
    private String descricao;
    private String apresentador;

    public Podcast(String titulo, String descricao, int duracao, String apresentador) {
        super(titulo, duracao);
        this.descricao = descricao;
        this.apresentador = apresentador;
    }

    public String getDescricao() { return descricao; }
    public String getApresentador() { return apresentador; }
}
