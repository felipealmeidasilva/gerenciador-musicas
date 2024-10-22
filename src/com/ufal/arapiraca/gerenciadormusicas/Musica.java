package com.ufal.arapiraca.gerenciadormusicas;


class Musica {
    private String titulo;
    private String artista;
    private String album;
    private GeneroMusical genero;
    private int duracao; // Em segundos
    private String dataLancamento;
    private int faixaEtariaMinima;
    private String compositor;
    private boolean permissaoCopyright;


    public Musica(String titulo, String artista, String album, GeneroMusical genero, int duracao, String dataLancamento, int faixaEtariaMinima, String compositor, boolean permissaoCopyright) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.duracao = duracao;
        this.dataLancamento = dataLancamento;
        this.faixaEtariaMinima = faixaEtariaMinima;
        this.compositor = compositor;
        this.permissaoCopyright = permissaoCopyright;
    }

    public Musica(String titulo, String artista, int duracao) {
    }

    public Album criarAlbum(String nomeAlbum) {
        Album album = new Album(nomeAlbum);
        album.adicionarMusica(this); // Adiciona a própria música ao álbum
        return album;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public GeneroMusical getGenero() {
        return genero;
    }

    public void setGenero(GeneroMusical genero) {
        this.genero = genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public int getFaixaEtariaMinima() {
        return faixaEtariaMinima;
    }

    public void setFaixaEtariaMinima(int faixaEtariaMinima) {
        this.faixaEtariaMinima = faixaEtariaMinima;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public boolean isPermissaoCopyright() {
        return permissaoCopyright;
    }

    public void setPermissaoCopyright(boolean permissaoCopyright) {
        this.permissaoCopyright = permissaoCopyright;
    }
}