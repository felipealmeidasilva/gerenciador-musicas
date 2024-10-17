package com.ufal.arapiraca.gerenciadormusicas;

// Classe específica para o artista
class Artista extends Usuario {
    private String nomeArtistico;
    private String nomeEmpresario;

    // Construtor
    public Artista(String nome, int idade, String cpf, String nacionalidade, String senha, String nomeArtistico, String nomeEmpresario) {
        super(nome, idade, cpf, nacionalidade, senha);
        this.nomeArtistico = nomeArtistico;
        this.nomeEmpresario = nomeEmpresario;
    }

    // Getters e Setters
    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }

    public String getNomeEmpresario() {
        return nomeEmpresario;
    }

    public void setNomeEmpresario(String nomeEmpresario) {
        this.nomeEmpresario = nomeEmpresario;
    }

    // Método para publicar música
    public void publicarMusica(String compositor, boolean permissaoCopyright) {
        // Lógica para publicar música
        System.out.println("Música publicada pelo artista: " + this.nomeArtistico);
        System.out.println("Compositor: " + compositor);
        System.out.println("Permissão de Copyright: " + permissaoCopyright);
    }
}