package com.ufal.arapiraca.gerenciadormusicas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String ARQUIVO_USUARIOS = "src/com/ufal/arapiraca/gerenciadormusicas/saves/usuarios.txt";
    private static final String ARQUIVO_MUSICAS = "src/com/ufal/arapiraca/gerenciadormusicas/saves/musicas.txt";
    private static final String ARQUIVO_PODCASTS = "src/com/ufal/arapiraca/gerenciadormusicas/saves/podcasts.txt";
    private static final String ARQUIVO_PLAYLISTS = "src/com/ufal/arapiraca/gerenciadormusicas/saves/playlists.txt";
    private static final String ARQUIVO_HISTORICO = "src/com/ufal/arapiraca/gerenciadormusicas/saves/historico.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Pessoa> pessoas = carregarUsuarios();
        List<Musica> musicas = carregarMusicas();
        List<Podcast> podcasts = carregarPodcasts();
        List<Playlist> playlists = carregarPlaylists();

        while (true) {
            System.out.println("------------- MENU -------------");
            System.out.println("1. Cadastrar");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            System.out.print("Digite a opção desejada: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    cadastrarUsuario(pessoas, scanner);
                    salvarUsuarios(pessoas);
                }
                case 2 -> {
                    Pessoa usuario = fazerLogin(pessoas, scanner);
                    if (usuario != null) {
                        if (usuario instanceof Artista) {
                            menuArtista((Artista) usuario, musicas, scanner);
                        } else if (usuario instanceof Produtor) {
                            menuProdutor((Produtor) usuario, podcasts, scanner);
                        } else if (usuario instanceof Ouvinte) {
                            Ouvinte ouvinte = (Ouvinte) usuario;
                            boolean executando = true;
                            while (executando) {
                                System.out.println("------------- MENU OUVINTE -------------");
                                System.out.println("1. Ouvir música");
                                System.out.println("2. Ouvir podcast");
                                System.out.println("3. Visualizar histórico");
                                System.out.println("4. Criar playlist");
                                System.out.println("5. Adicionar música a uma playlist");
                                System.out.println("6. Adicionar podcast a uma playlist");
                                System.out.println("7. Sair");
                                System.out.print("Escolha uma opção: ");
                                
                                int entrada = scanner.nextInt();
                                scanner.nextLine();
                
                                switch (entrada) {
                                    case 1 -> ouvinte.ouvirMusica(musicas, scanner);
                                    case 2 -> ouvinte.ouvirPodcast(podcasts, scanner);
                                    case 3 -> ouvinte.visualizarHistorico();
                                    case 4 -> ouvinte.criarPlaylist(playlists, scanner);
                                    case 5 -> ouvinte.adicionarMusicaPlaylist(playlists, musicas, scanner);
                                    case 6 -> ouvinte.adicionarPodcastPlaylist(playlists, podcasts, scanner);
                                    case 7 -> executando = false;
                                    default -> System.out.println("Opção inválida! Tente novamente.");
                                }
                            }
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    salvarUsuarios(pessoas);
                    salvarMusicas(musicas);
                    salvarPodcasts(podcasts);
                    salvarPlaylists(playlists);
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
          }
        }
    private static void menuArtista(Artista artista, List<Musica> musicas, Scanner scanner) {
        System.out.println("Bem-vindo(a), Artista " + artista.getNomeArtistico());

        while (true) {
            System.out.println("------------- MENU Artista -------------");
            System.out.println("1. Cadastrar uma música");
            System.out.println("2. Voltar");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.print("Digite o título da música: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o álbum: ");
                    String album = scanner.nextLine();
                    System.out.print("Escolha o gênero (POP, ROCK, JAZZ, etc.): ");
                    GeneroMusical genero = GeneroMusical.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Duração (em segundos): ");
                    int duracao = scanner.nextInt();
                    scanner.nextLine(); // Consumir newline
                    System.out.print("Data de lançamento: ");
                    String dataLancamento = scanner.nextLine();
                    System.out.print("Faixa etária mínima: ");
                    int faixaEtariaMinima = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Compositor: ");
                    String compositor = scanner.nextLine();
                    System.out.print("Permissão de Copyright (true/false): ");
                    boolean permissaoCopyright = scanner.nextBoolean();
            
                    artista.publicarMusica(musicas, titulo, album, genero, duracao, dataLancamento, faixaEtariaMinima, compositor, permissaoCopyright);
                    salvarMusicas(musicas);
                    return;
                
                case 2:
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            } 
        }
        

       
    }

    private static void menuProdutor(Produtor produtor, List<Podcast> podcasts, Scanner scanner) {
        System.out.println("Bem-vindo(a), Produtor " + produtor.getNomeProfissional());
        while (true) {
            System.out.println("------------- MENU Produtor -------------");
            System.out.println("1. Cadastrar um podcast");
            System.out.println("2. Voltar");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao){
                case 1:
                System.out.print("Digite o título do podcast: ");
                String titulo = scanner.nextLine();
                System.out.print("Digite a descrição: ");
                String descricao = scanner.nextLine();
                System.out.print("Duração (em segundos): ");
                int duracao = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Apresentador: ");
                String apresentador = scanner.nextLine();

                produtor.publicarPodcast(podcasts, titulo, descricao, duracao, apresentador);
                salvarPodcasts(podcasts);
                case 2:
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
            }
        
    }

    private static void cadastrarUsuario(List<Pessoa> pessoas, Scanner scanner) {
        System.out.println("------------- CADASTRO -------------");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Nacionalidade: ");
        String nacionalidade = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.println("------------- TIPO DE USUÁRIO -------------");
        System.out.println("1. Artista");
        System.out.println("2. Produtor");
        System.out.println("3. Ouvinte");
        System.out.print("Digite a opção desejada: ");

        int tipoUsuario = scanner.nextInt();
        scanner.nextLine();

        Pessoa novaPessoa = switch (tipoUsuario) {
            case 1 -> {
                System.out.print("Nome Artístico: ");
                String nomeArtistico = scanner.nextLine();
                System.out.print("Nome do Empresário: ");
                String nomeEmpresario = scanner.nextLine();
                yield new Artista(nome, idade, cpf, nacionalidade, senha, nomeArtistico, nomeEmpresario);
            }
            case 2 -> {
                System.out.print("Nome Profissional: ");
                String nomeProfissional = scanner.nextLine();
                yield new Produtor(nome, idade, cpf, nacionalidade, senha, nomeProfissional);
            }
            case 3 -> {
                System.out.print("Nome de Exibição: ");
                String nomeExibicao = scanner.nextLine();
                yield new Ouvinte(nome, idade, cpf, nacionalidade, senha, nomeExibicao);
            }
            default -> throw new IllegalArgumentException("Opção inválida!");
        };

        pessoas.add(novaPessoa);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static Pessoa fazerLogin(List<Pessoa> pessoas, Scanner scanner) {
        System.out.println("------------- LOGIN -------------");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
    
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
    
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf) && pessoa.getSenha().equals(senha)) {
                System.out.println("Login realizado com sucesso!");
                System.out.println("Bem-vindo, " + pessoa.getNome() + "!");
                return pessoa; // Retorna o objeto Pessoa que fez o login com sucesso
            }
        }
    
        System.out.println("Usuário ou senha inválidos!");
        return null; // Retorna null se o login falhar
    }

    private static void salvarUsuarios(List<Pessoa> pessoas) {
        try (PrintWriter writer = new PrintWriter(ARQUIVO_USUARIOS)) {
            for (Pessoa pessoa : pessoas) {
                writer.println(pessoa.getClass().getSimpleName() + ";" + pessoa.getNome() + ";" + pessoa.getIdade() + ";" +
                        pessoa.getCpf() + ";" + pessoa.getNacionalidade() + ";" + pessoa.getSenha());

                if (pessoa instanceof Artista artista) {
                    writer.println("Nome Artístico:" + artista.getNomeArtistico());
                    writer.println("Nome do Empresário:" + artista.getNomeEmpresario());
                } else if (pessoa instanceof Produtor produtor) {
                    writer.println("Nome Profissional:" + produtor.getNomeProfissional());
                } else if (pessoa instanceof Ouvinte ouvinte) {
                    writer.println("Nome de Exibição:" + ouvinte.getNomeExibicao());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar os usuários: " + e.getMessage());
        }
    }

    private static List<Pessoa> carregarUsuarios() {
        List<Pessoa> pessoas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                String tipo = partes[0];
                String nome = partes[1];
                int idade = Integer.parseInt(partes[2]);
                String cpf = partes[3];
                String nacionalidade = partes[4];
                String senha = partes[5];

                Pessoa pessoa = switch (tipo) {
                    case "Artista" -> new Artista(nome, idade, cpf, nacionalidade, senha,
                            reader.readLine().split(":")[1], reader.readLine().split(":")[1]);
                    case "Produtor" -> new Produtor(nome, idade, cpf, nacionalidade, senha,
                            reader.readLine().split(":")[1]);
                    case "Ouvinte" -> new Ouvinte(nome, idade, cpf, nacionalidade, senha,
                            reader.readLine().split(":")[1]);
                    default -> throw new IllegalArgumentException("Tipo de usuário desconhecido: " + tipo);
                };
                pessoas.add(pessoa);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar os usuários: " + e.getMessage());
        }
        return pessoas;
    }

    private static void salvarMusicas(List<Musica> musicas) {
        try (PrintWriter writer = new PrintWriter(ARQUIVO_MUSICAS)) {
            for (Musica musica : musicas) {
                writer.println(musica.getTitulo() + ";" + musica.getArtista() + ";" + musica.getAlbum() + ";" +
                        musica.getGenero() + ";" + musica.getDuracao() + ";" + musica.getDataLancamento() + ";" +
                        musica.getFaixaEtariaMinima() + ";" + musica.getCompositor() + ";" + musica.isPermissaoCopyright());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar as músicas: " + e.getMessage());
        }
    }

    private static List<Musica> carregarMusicas() {
        List<Musica> musicas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_MUSICAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                String titulo = partes[0];
                String artista = partes[1];
                String album = partes[2];
                GeneroMusical genero = GeneroMusical.valueOf(partes[3]);
                int duracao = Integer.parseInt(partes[4]);
                String dataLancamento = partes[5];
                int faixaEtariaMinima = Integer.parseInt(partes[6]);
                String compositor = partes[7];
                boolean permissaoCopyright = Boolean.parseBoolean(partes[8]);

                Musica musica = new Musica(titulo, artista, album, genero, duracao, dataLancamento, faixaEtariaMinima, compositor, permissaoCopyright);
                musicas.add(musica);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar as músicas: " + e.getMessage());
        }
        return musicas;
    }

    private static void salvarPodcasts(List<Podcast> podcasts) {
        try (PrintWriter writer = new PrintWriter(ARQUIVO_PODCASTS)) {
            for (Podcast podcast : podcasts) {
                writer.println(podcast.getTitulo() + ";" + podcast.getDescricao() + ";" + podcast.getDuracao() + ";" + podcast.getApresentador());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar os podcasts: " + e.getMessage());
        }
    }

    private static List<Podcast> carregarPodcasts() {
        List<Podcast> podcasts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PODCASTS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                String titulo = partes[0];
                String descricao = partes[1];
                int duracao = Integer.parseInt(partes[2]);
                String apresentador = partes[3];

                Podcast podcast = new Podcast(titulo, descricao, duracao, apresentador);
                podcasts.add(podcast);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar os podcasts: " + e.getMessage());
        }
        return podcasts;
    }

    private static void salvarPlaylists(List<Playlist> playlists) {
        try (PrintWriter writer = new PrintWriter(ARQUIVO_PLAYLISTS)) {
            for (Playlist playlist : playlists) {
                writer.println(playlist instanceof PlaylistMusica ? "Musica" : "Podcast");
                writer.println(playlist.getNome());
                for (Audio audio : playlist.getAudios()) {
                    writer.println(audio.getTitulo() + ";" + audio.getDuracao());
                }
                writer.println("FIM_PLAYLIST");
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar as playlists: " + e.getMessage());
        }
    }

    private static List<Playlist> carregarPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PLAYLISTS))) {
            String linha;
            Playlist playlistAtual = null;
            while ((linha = reader.readLine()) != null) {
                if (linha.equals("FIM_PLAYLIST")) {
                    if (playlistAtual != null) playlists.add(playlistAtual);
                    playlistAtual = null;
                } else if (linha.equals("Musica") || linha.equals("Podcast")) {
                    playlistAtual = PlaylistFactory.criarPlaylist(linha, reader.readLine());
                } else {
                    String[] partes = linha.split(";");
                    playlistAtual.adicionarAudio(new Audio(partes[0], Integer.parseInt(partes[1])));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar as playlists: " + e.getMessage());
        }
        return playlists;
    }

    private static void salvarHistorico(Ouvinte ouvinte) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO_HISTORICO, true))) {
            for (Audio audio : ouvinte.getHistoricoMusicas()) {
                writer.println(ouvinte.getCpf() + ";" + audio.getTitulo() + ";" + audio.getDuracao());
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }

    private static List<Audio> carregarHistorico(String cpf) {
        List<Audio> historico = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_HISTORICO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes[0].equals(cpf)) {
                    String titulo = partes[1];
                    int duracao = Integer.parseInt(partes[2]);
                    historico.add(new Audio(titulo, duracao));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar o histórico: " + e.getMessage());
        }
        return historico;
    }
}
