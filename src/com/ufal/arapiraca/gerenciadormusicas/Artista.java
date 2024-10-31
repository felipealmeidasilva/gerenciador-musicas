package com.ufal.arapiraca.gerenciadormusicas;

import java.util.List;

public class Artista extends Pessoa {
    private String nomeArtistico;
    private String nomeEmpresario;

    public Artista(String nome, int idade, String cpf, String nacionalidade, String senha, String nomeArtistico, String nomeEmpresario) {
        super(nome, idade, cpf, nacionalidade, senha);
        this.nomeArtistico = nomeArtistico;
        this.nomeEmpresario = nomeEmpresario;
    }

    public String getNomeArtistico() { return nomeArtistico; }
    public String getNomeEmpresario() { return nomeEmpresario; }
    public void publicarMusica(List<Musica> musicas, String titulo, String album, GeneroMusical genero, int duracao, String dataLancamento, int faixaEtariaMinima, String compositor, boolean permissaoCopyright) {
        Musica novaMusica = new Musica(titulo, this.nomeArtistico, album, genero, duracao, dataLancamento, faixaEtariaMinima, compositor, permissaoCopyright);
        musicas.add(novaMusica);
        System.out.println("Música '" + titulo + "' publicada com sucesso por " + nomeArtistico);
    }
}
