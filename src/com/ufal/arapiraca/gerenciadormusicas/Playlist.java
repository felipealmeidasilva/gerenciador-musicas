package com.ufal.arapiraca.gerenciadormusicas;

import java.util.ArrayList;
import java.util.List;

public abstract class Playlist {
    private String nome;
    private List<Audio> audios;

    public Playlist(String nome) {
        this.nome = nome;
        this.audios = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public List<Audio> getAudios() { return audios; }

    public void adicionarAudio(Audio audio) {
        audios.add(audio);
    }
}
