package com.ufal.arapiraca.gerenciadormusicas;

public class Musica extends Audio {
    private String artista;
    private String album;
    private GeneroMusical genero;
    private String compositor;
    private String dataLancamento;
    private boolean permissaoCopyright;
    private int faixaEtariaMinima;

    public Musica(String titulo, String artista, String album, GeneroMusical genero, int duracao, String dataLancamento, int faixaEtariaMinima, String compositor, boolean permissaoCopyright) {
        super(titulo, duracao);
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.compositor = compositor;
        this.permissaoCopyright = permissaoCopyright;
        this.faixaEtariaMinima = faixaEtariaMinima;
    }

    public String getArtista() { return artista; }
    public String getAlbum() { return album; }
    public GeneroMusical getGenero() { return genero; }
    public String getCompositor() { return compositor; }
    public String getDataLancamento(){return dataLancamento;}
    public boolean isPermissaoCopyright() { return permissaoCopyright; }
    public int getFaixaEtariaMinima() { return faixaEtariaMinima; }
}
