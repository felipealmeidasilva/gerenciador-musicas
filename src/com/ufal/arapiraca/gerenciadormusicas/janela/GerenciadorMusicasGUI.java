package com.ufal.arapiraca.gerenciadormusicas.janela;

import com.ufal.arapiraca.gerenciadormusicas.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GerenciadorMusicasGUI {
    private List<Pessoa> pessoas;
    private List<Musica> musicas;
    private List<Podcast> podcasts;
    private List<Playlist> playlists;

    public GerenciadorMusicasGUI(List<Pessoa> pessoas, List<Musica> musicas, List<Podcast> podcasts, List<Playlist> playlists) {
        this.pessoas = pessoas;
        this.musicas = musicas;
        this.podcasts = podcasts;
        this.playlists = playlists;
    }

    public void iniciar() {
        while (true) {
            String[] opcoes = {"Cadastrar", "Login", "Sair"};
            int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);

            switch (opcao) {
                case 0 -> cadastrarUsuario();
                case 1 -> {
                    Pessoa usuario = fazerLogin();
                    if (usuario != null) {
                        if (usuario instanceof Artista) {
                            menuArtista((Artista) usuario);
                        } else if (usuario instanceof Produtor) {
                            menuProdutor((Produtor) usuario);
                        } else if (usuario instanceof Ouvinte) {
                            menuOuvinte((Ouvinte) usuario);
                        }
                    }
                }
                case 2 -> {
                    JOptionPane.showMessageDialog(null, "Saindo do programa...");
                    System.exit(0);
                }
            }
        }
    }

    private void cadastrarUsuario() {
        String nome = JOptionPane.showInputDialog("Nome:");
        int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade:"));
        String cpf = JOptionPane.showInputDialog("CPF:");
        String nacionalidade = JOptionPane.showInputDialog("Nacionalidade:");
        String senha = JOptionPane.showInputDialog("Senha:");

        String[] tipos = {"Artista", "Produtor", "Ouvinte"};
        int tipoUsuario = JOptionPane.showOptionDialog(null, "Escolha o tipo de usuário:", "Tipo de Usuário",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);

        Pessoa novaPessoa = switch (tipoUsuario) {
            case 0 -> {
                String nomeArtistico = JOptionPane.showInputDialog("Nome Artístico:");
                String nomeEmpresario = JOptionPane.showInputDialog("Nome do Empresário:");
                yield new Artista(nome, idade, cpf, nacionalidade, senha, nomeArtistico, nomeEmpresario);
            }
            case 1 -> {
                String nomeProfissional = JOptionPane.showInputDialog("Nome Profissional:");
                yield new Produtor(nome, idade, cpf, nacionalidade, senha, nomeProfissional);
            }
            case 2 -> {
                String nomeExibicao = JOptionPane.showInputDialog("Nome de Exibição:");
                yield new Ouvinte(nome, idade, cpf, nacionalidade, senha, nomeExibicao);
            }
            default -> throw new IllegalArgumentException("Opção inválida!");
        };

        pessoas.add(novaPessoa);
        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
    }

    private Pessoa fazerLogin() {
        String cpf = JOptionPane.showInputDialog("CPF:");
        String senha = JOptionPane.showInputDialog("Senha:");

        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf) && pessoa.getSenha().equals(senha)) {
                JOptionPane.showMessageDialog(null, "Login realizado com sucesso! Bem-vindo, " + pessoa.getNome());
                return pessoa;
            }
        }
        JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
        return null;
    }

    private void menuArtista(Artista artista) {
        JOptionPane.showMessageDialog(null, "Bem-vindo(a), Artista " + artista.getNomeArtistico());
        String titulo = JOptionPane.showInputDialog("Digite o título da música:");
        String album = JOptionPane.showInputDialog("Digite o álbum:");
        GeneroMusical genero = GeneroMusical.valueOf(JOptionPane.showInputDialog("Escolha o gênero (POP, ROCK, JAZZ, etc.):").toUpperCase());
        int duracao = Integer.parseInt(JOptionPane.showInputDialog("Duração (em segundos):"));
        String dataLancamento = JOptionPane.showInputDialog("Data de lançamento:");
        int faixaEtariaMinima = Integer.parseInt(JOptionPane.showInputDialog("Faixa etária mínima:"));
        String compositor = JOptionPane.showInputDialog("Compositor:");
        boolean permissaoCopyright = JOptionPane.showConfirmDialog(null, "Permissão de Copyright?", "Copyright", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        artista.publicarMusica(musicas, titulo, album, genero, duracao, dataLancamento, faixaEtariaMinima, compositor, permissaoCopyright);
        JOptionPane.showMessageDialog(null, "Música publicada com sucesso!");
    }

    private void menuProdutor(Produtor produtor) {
        JOptionPane.showMessageDialog(null, "Bem-vindo(a), Produtor " + produtor.getNomeProfissional());
        String titulo = JOptionPane.showInputDialog("Digite o título do podcast:");
        String descricao = JOptionPane.showInputDialog("Digite a descrição:");
        int duracao = Integer.parseInt(JOptionPane.showInputDialog("Duração (em segundos):"));
        String apresentador = JOptionPane.showInputDialog("Apresentador:");

        produtor.publicarPodcast(podcasts, titulo, descricao, duracao, apresentador);
        JOptionPane.showMessageDialog(null, "Podcast publicado com sucesso!");
    }

    private void menuOuvinte(Ouvinte ouvinte) {
        String[] opcoes = {"Ouvir Música", "Ouvir Podcast", "Visualizar Histórico", "Criar Playlist", "Ouvir Playlist", "Sair"};
        while (true) {
            int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:", "Menu Ouvinte",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
    
            switch (opcao) {
                case 0 -> ouvinte.ouvirMusica(musicas); // Assumindo que 'musicas' seja uma lista de músicas inicializada
                case 1 -> ouvinte.ouvirPodcast(podcasts); // Assumindo que 'podcasts' seja uma lista de podcasts inicializada
                case 2 -> ouvinte.visualizarHistorico();
                case 3 -> ouvinte.criarPlaylist(playlists);
                case 4 -> ouvinte.ouvirPlaylist(playlists);
                case 6 -> ouvinte.adicionarAFavoritos(musicas, podcasts);
                case 5 -> {
                    return;
                }
                default -> JOptionPane.showMessageDialog(null, "Escolha inválida. Tente novamente.");
            }
        }
    }
}