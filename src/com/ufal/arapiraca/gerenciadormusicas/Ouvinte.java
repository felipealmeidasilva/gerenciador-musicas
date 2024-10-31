package com.ufal.arapiraca.gerenciadormusicas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;


public class Ouvinte extends Pessoa {
    private String nomeExibicao;
    private List<Musica> historicoMusicas;
    private List<Podcast> historicoPodcasts;
    private Playlist favoritos;

    public Ouvinte(String nome, int idade, String cpf, String nacionalidade, String senha, String nomeExibicao) {
        super(nome, idade, cpf, nacionalidade, senha);
        this.nomeExibicao = nomeExibicao;
        this.historicoMusicas = new ArrayList<>();
        this.historicoPodcasts = new ArrayList<>();
        this.favoritos = PlaylistFactory.criarPlaylist("favoritos", "Minha Playlist de Favoritos");
    }

    public String getNomeExibicao() { return nomeExibicao; }

    public void ouvirPlaylist(List<Playlist> playlists) {
        String[] nomesPlaylists = playlists.stream().map(Playlist::getNome).toArray(String[]::new);
        String escolha = (String) JOptionPane.showInputDialog(null, "Escolha uma playlist para ouvir:",
                "Ouvir Playlist", JOptionPane.QUESTION_MESSAGE, null, nomesPlaylists, nomesPlaylists[0]);

        if (escolha != null) {
            Playlist playlistEscolhida = playlists.stream().filter(p -> p.getNome().equals(escolha)).findFirst().orElse(null);
            if (playlistEscolhida != null) {
                JOptionPane.showMessageDialog(null, "Reproduzindo playlist: " + playlistEscolhida.getNome());
                playlistEscolhida.getAudios().forEach(audio ->
                        JOptionPane.showMessageDialog(null, "Reproduzindo: " + audio.getTitulo()));
            }
        }
    }

    public void ouvirMusica(List<Musica> musicas) {
        if (musicas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existem músicas disponíveis.");
            return;
        }
    
        String[] titulos = musicas.stream().map(Musica::getTitulo).toArray(String[]::new);
        String escolha = (String) JOptionPane.showInputDialog(null, "Escolha uma música para ouvir:",
                "Ouvir Música", JOptionPane.PLAIN_MESSAGE, null, titulos, titulos[0]);
    
        if (escolha != null) {
            Musica musicaEscolhida = musicas.stream().filter(m -> m.getTitulo().equals(escolha)).findFirst().orElse(null);
            if (musicaEscolhida != null) {
                historicoMusicas.add(musicaEscolhida);
                JOptionPane.showMessageDialog(null, "Você está ouvindo: " + musicaEscolhida.getTitulo());
            }
        }
    }

    public void ouvirPodcast(List<Podcast> podcasts) {
        if (podcasts.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existem podcasts disponíveis.");
            return;
        }
    
        String[] titulos = podcasts.stream().map(Podcast::getTitulo).toArray(String[]::new);
        String escolha = (String) JOptionPane.showInputDialog(null, "Escolha um podcast para ouvir:",
                "Ouvir Podcast", JOptionPane.PLAIN_MESSAGE, null, titulos, titulos[0]);
    
        if (escolha != null) {
            Podcast podcastEscolhido = podcasts.stream().filter(p -> p.getTitulo().equals(escolha)).findFirst().orElse(null);
            if (podcastEscolhido != null) {
                historicoPodcasts.add(podcastEscolhido);
                JOptionPane.showMessageDialog(null, "Você está ouvindo: " + podcastEscolhido.getTitulo());
            }
        }
    }

    public void visualizarHistorico() {
        StringBuilder historico = new StringBuilder("----- Histórico de Escuta -----\nMúsicas:\n");
        historicoMusicas.forEach(musica -> historico.append("- ").append(musica.getTitulo()).append("\n"));
        historico.append("Podcasts:\n");
        historicoPodcasts.forEach(podcast -> historico.append("- ").append(podcast.getTitulo()).append("\n"));
        JOptionPane.showMessageDialog(null, historico.toString());
    }

    public void criarPlaylist(List<Playlist> playlists) {
        String nomePlaylist = JOptionPane.showInputDialog(null, "Digite o nome da nova playlist:", "Criar Playlist",
                JOptionPane.PLAIN_MESSAGE);
        if (nomePlaylist != null && !nomePlaylist.isEmpty()) {
            Playlist novaPlaylist = new Playlist(nomePlaylist){};
            playlists.add(novaPlaylist);
            JOptionPane.showMessageDialog(null, "Playlist '" + nomePlaylist + "' criada com sucesso!");
        }
    }
    public void adicionarAFavoritos(List<Musica> musicas, List<Podcast> podcasts){
        return;
    }
}
