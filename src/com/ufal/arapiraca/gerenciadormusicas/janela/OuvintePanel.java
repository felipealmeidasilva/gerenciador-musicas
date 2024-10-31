package com.ufal.arapiraca.gerenciadormusicas.janela;

import com.ufal.arapiraca.gerenciadormusicas.Ouvinte;
import javax.swing.*;

public class OuvintePanel extends JPanel {
    private Ouvinte ouvinte;

    public OuvintePanel(Ouvinte ouvinte) {
        this.ouvinte = ouvinte;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton ouvirMusicaButton = new JButton("Ouvir Música");
        JButton ouvirPodcastButton = new JButton("Ouvir Podcast");
        JButton criarPlaylistButton = new JButton("Criar Playlist");

        add(ouvirMusicaButton);
        add(ouvirPodcastButton);
        add(criarPlaylistButton);

        ouvirMusicaButton.addActionListener(e -> ouvirMusica());
        ouvirPodcastButton.addActionListener(e -> ouvirPodcast());
        criarPlaylistButton.addActionListener(e -> criarPlaylist());
    }

    private void ouvirMusica() {
        // Lógica para exibir ou selecionar músicas
    }

    private void ouvirPodcast() {
        // Lógica para exibir ou selecionar podcasts
    }

    private void criarPlaylist() {
        // Lógica para criar playlists
    }
}
