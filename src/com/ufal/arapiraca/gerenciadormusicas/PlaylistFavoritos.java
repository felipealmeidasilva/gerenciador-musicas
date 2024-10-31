package com.ufal.arapiraca.gerenciadormusicas;

public class PlaylistFavoritos extends Playlist {

    public PlaylistFavoritos(String nome) {
        super(nome);
    }

    @Override
    public void adicionarAudio(Audio audio) {
        if (!getAudios().contains(audio)) {
            super.adicionarAudio(audio);
        } else {
            System.out.println("Este áudio já está na playlist de favoritos.");
        }
    }
}