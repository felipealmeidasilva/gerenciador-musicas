package com.ufal.arapiraca.gerenciadormusicas;

public class Audio {
    private String titulo;
    private int duracao;

    public Audio(String titulo, int duracao) {
        this.titulo = titulo;
        this.duracao = duracao;
    }

    public String getTitulo() { return titulo; }
    public int getDuracao() { return duracao; }
}
