package com.ufal.arapiraca.gerenciadormusicas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ouvinte extends Pessoa {
    private String nomeExibicao;
    private List<Musica> historicoMusicas;
    private List<Podcast> historicoPodcasts;


    public Ouvinte(String nome, int idade, String cpf, String nacionalidade, String senha, String nomeExibicao) {
        super(nome, idade, cpf, nacionalidade, senha);
        this.nomeExibicao = nomeExibicao;
        this.historicoMusicas = new ArrayList<>();
        this.historicoPodcasts = new ArrayList<>();
    }
 

    public String getNomeExibicao() { return nomeExibicao; }
    
    public void ouvirMusica(List<Musica> musicas, Scanner scanner) {
        System.out.println("----- Escolha uma música para ouvir -----");
        for (int i = 0; i < musicas.size(); i++) {
            System.out.println((i + 1) + ". " + musicas.get(i).getTitulo());
        }
        System.out.print("Escolha a música pelo número: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha > 0 && escolha <= musicas.size()) {
            Musica musicaEscolhida = musicas.get(escolha - 1);
            historicoMusicas.add(musicaEscolhida);
            System.out.println("Você está ouvindo: " + musicaEscolhida.getTitulo());
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    public void ouvirPodcast(List<Podcast> podcasts, Scanner scanner) {
        System.out.println("----- Escolha um podcast para ouvir -----");
        for (int i = 0; i < podcasts.size(); i++) {
            System.out.println((i + 1) + ". " + podcasts.get(i).getTitulo());
        }
        System.out.print("Escolha o podcast pelo número: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha > 0 && escolha <= podcasts.size()) {
            Podcast podcastEscolhido = podcasts.get(escolha - 1);
            historicoPodcasts.add(podcastEscolhido);
            System.out.println("Você está ouvindo: " + podcastEscolhido.getTitulo());
        } else {
            System.out.println("Escolha inválida.");
        }
    }

    public void visualizarHistorico() {
        System.out.println("----- Histórico de Escuta -----");
        System.out.println("Músicas:");
        for (Musica musica : historicoMusicas) {
            System.out.println("- " + musica.getTitulo());
        }
        System.out.println("Podcasts:");
        for (Podcast podcast : historicoPodcasts) {
            System.out.println("- " + podcast.getTitulo());
        }
    }

    public void criarPlaylist(List<Playlist> playlists, Scanner scanner) {
        System.out.print("Digite o nome da nova playlist: ");
        String nomePlaylist = scanner.nextLine();
        Playlist novaPlaylist = new Playlist(nomePlaylist) {};
        playlists.add(novaPlaylist);
        System.out.println("Playlist '" + nomePlaylist + "' criada com sucesso!");
    }

    public void adicionarMusicaPlaylist(List<Playlist> playlists, List<Musica> musicas, Scanner scanner) {
        System.out.println("Escolha uma playlist:");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).getNome());
        }
        int escolhaPlaylist = scanner.nextInt();
        scanner.nextLine();

        if (escolhaPlaylist > 0 && escolhaPlaylist <= playlists.size()) {
            Playlist playlistEscolhida = playlists.get(escolhaPlaylist - 1);
            System.out.println("Escolha uma música para adicionar:");
            for (int i = 0; i < musicas.size(); i++) {
                System.out.println((i + 1) + ". " + musicas.get(i).getTitulo());
            }
            int escolhaMusica = scanner.nextInt();
            scanner.nextLine();

            if (escolhaMusica > 0 && escolhaMusica <= musicas.size()) {
                playlistEscolhida.adicionarAudio(musicas.get(escolhaMusica - 1));
                System.out.println("Música adicionada à playlist com sucesso!");
            } else {
                System.out.println("Escolha inválida de música.");
            }
        } else {
            System.out.println("Escolha inválida de playlist.");
        }
    }

    public void adicionarPodcastPlaylist(List<Playlist> playlists, List<Podcast> podcasts, Scanner scanner) {
        System.out.println("Escolha uma playlist:");
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).getNome());
        }
        int escolhaPlaylist = scanner.nextInt();
        scanner.nextLine();

        if (escolhaPlaylist > 0 && escolhaPlaylist <= playlists.size()) {
            Playlist playlistEscolhida = playlists.get(escolhaPlaylist - 1);
            System.out.println("Escolha um podcast para adicionar:");
            for (int i = 0; i < podcasts.size(); i++) {
                System.out.println((i + 1) + ". " + podcasts.get(i).getTitulo());
            }
            int escolhaPodcast = scanner.nextInt();
            scanner.nextLine();

            if (escolhaPodcast > 0 && escolhaPodcast <= podcasts.size()) {
                playlistEscolhida.adicionarAudio(podcasts.get(escolhaPodcast - 1));
                System.out.println("Podcast adicionado à playlist com sucesso!");
            } else {
                System.out.println("Escolha inválida de podcast.");
            }
        } else {
            System.out.println("Escolha inválida de playlist.");
        }
    }

    public List<Musica> getHistoricoMusicas() {
        return historicoMusicas;
    }

    public List<Podcast> getHistoricoPodcasts() {
        return historicoPodcasts;
    }
}
