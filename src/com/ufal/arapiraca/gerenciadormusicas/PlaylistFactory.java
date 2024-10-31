package com.ufal.arapiraca.gerenciadormusicas;

public class PlaylistFactory {
    public static Playlist criarPlaylist(String tipo, String nome) {
        return switch (tipo.toLowerCase()) {
            case "musica" -> new PlaylistMusica(nome);
            case "podcast" -> new PlaylistPodcast(nome);
            case "favoritos" -> new PlaylistFavoritos(nome);
            default -> throw new IllegalArgumentException("Tipo de playlist inválido");
        };
    }
    
}
