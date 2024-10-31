package com.ufal.arapiraca.gerenciadormusicas;

public class PlaylistFactory {
    public static Playlist criarPlaylist(String tipo, String nome) {
        return switch (tipo) {
            case "Musica" -> new PlaylistMusica(nome);
            case "Podcast" -> new PlaylistPodcast(nome);
            default -> throw new IllegalArgumentException("Tipo de playlist inválido");
        };
    }
}
