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
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        

        List<Pessoa> pessoas = carregarUsuarios();
        List<Musica> musicas = carregarMusicas();
        List<Podcast> podcasts = carregarPodcasts();
        List<Playlist> playlists = carregarPlaylists();

        System.out.println(pessoas);

        while (true) {

            System.out.println("------------- MENU -------------");
            System.out.println("1. Cadastrar");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            System.out.println("------------------------------");
            System.out.print("Digite a opção desejada: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> {
                    // Cadastro de usuário
                    cadastrarUsuario(pessoas, scanner, null);
                    salvarUsuarios(pessoas);
                }
                case 2 -> // Login de usuário
                    fazerLogin(pessoas, scanner);
                case 3 -> {
                    // Sair do programa
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


    
    //MétodocadastrarUsuario
    private static void cadastrarUsuario(List<Pessoa> pessoas, Scanner scanner, Pessoa pessoa) {
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
        System.out.println("------------------------------");
        System.out.print("Digite a opção desejada: ");

        int tipoUsuario = scanner.nextInt();
        scanner.nextLine(); // Ler a quebra de linha

        Pessoa novoPessoa = null;

        switch (tipoUsuario) {
            case 1 -> {
                // Cadastrar um artista
                System.out.print("Nome Artístico: ");
                String nomeArtistico = scanner.nextLine();

                System.out.print("Nome do Empresário: ");
                String nomeEmpresario = scanner.nextLine();

                novoPessoa = new Artista(nome, idade, cpf, nacionalidade, senha, nomeArtistico, nomeEmpresario);
            }
            case 2 -> {
                // Cadastrar um produtor
                System.out.print("Nome Profissional: ");
                String nomeProfissional = scanner.nextLine();

                pessoa = new Produtor(nome, idade, cpf, nacionalidade, senha, nomeProfissional);
            }
            case 3 -> {
                // Cadastrar um ouvinte
                System.out.print("Nome de Exibição: ");
                String nomeExibicao = scanner.nextLine();

                pessoa = new Ouvinte(nome, idade, cpf, nacionalidade, senha, nomeExibicao);
            }
            default -> System.out.println("Opção inválida!");
        }

        if (novoPessoa != null) {
            pessoas.add(novoPessoa);
            System.out.println("Usuário cadastrado com sucesso!");
            return;
        }
        
        throw new UnsupportedOperationException("Não temos essa implementação ainda.");
    }


    private static void fazerLogin(List<Pessoa> pessoas, Scanner scanner) {
        System.out.println("------------- LOGIN -------------");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();


        boolean usuarioEncontrado = false;
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCpf().equals(cpf) && pessoa.getSenha().equals(senha)) {
                usuarioEncontrado = true;
                System.out.println("Login realizado com sucesso!");

                // Aqui você pode direcionar o usuário para a tela principal do sistema,
                // de acordo com o seu tipo (artista, produtor ou ouvinte).
                System.out.println("Bem-vindo, " + pessoa.getNome() + "!");
                // ...
                break;
            }
            else {
                System.out.println("Usuário ou senha inválidos!");
                return;
                }
            }
        }


    private static void salvarUsuarios(List<Pessoa> pessoas) {
        try (PrintWriter writer = new PrintWriter(ARQUIVO_USUARIOS)) {
            for (Pessoa pessoa : pessoas) {

                writer.println(pessoa.getClass().getSimpleName() + ";" + pessoa.getNome() + ";" + pessoa.getIdade() + ";" + pessoa.getCpf() + ";" + pessoa.getNacionalidade() + ";" + pessoa.getSenha());


                if (pessoa instanceof Artista) {
                    Artista artista = (Artista) pessoa;
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
                if (partes.length > 0) {
                    String tipo = partes[0];
                    String nome = partes[1];
                    int idade = Integer.parseInt(partes[2]);
                    String cpf = partes[3];
                    String nacionalidade = partes[4];
                    String senha = partes[5];

                    Pessoa pessoa = null;

                    switch (tipo) {
                        case "Artista" -> {
                            String nomeArtistico = reader.readLine().split(":")[1];
                            String nomeEmpresario = reader.readLine().split(":")[1];
                            pessoa = new Artista(nome, idade, cpf, nacionalidade, senha, nomeArtistico, nomeEmpresario);
                        }
                        case "Produtor" -> {
                            String nomeProfissional = reader.readLine().split(":")[1];
                            pessoa = new Produtor(nome, idade, cpf, nacionalidade, senha, nomeProfissional);
                        }
                        case "Ouvinte" -> {
                            String nomeExibicao = reader.readLine().split(":")[1];
                            pessoa = new Ouvinte(nome, idade, cpf, nacionalidade, senha, nomeExibicao);
                        }
                    }

                    if (pessoa != null) {
                        pessoas.add(pessoa);
                    }
                }
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
                if (partes.length == 9) {
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
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar as músicas: " + e.getMessage());
        }
        return musicas;
    }


    private static void salvarPodcasts(List<Podcast> podcasts) {
        try (PrintWriter writer = new PrintWriter(ARQUIVO_PODCASTS)) {
            for (Podcast podcast : podcasts) {
                writer.println(podcast.getNomePodcast() + ";" + podcast.getProdutor() + ";" + podcast.getGenero() + ";" + podcast.getDuracao() + ";" + podcast.getDataLancamento());


                writer.println("Participantes:" + String.join(",", podcast.getParticipantes()));
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
                if (partes.length > 0) {
                    String nomePodcast = partes[0];
                    String produtor = partes[1];
                    GeneroPodcast genero = GeneroPodcast.valueOf(partes[2]);
                    int duracao = Integer.parseInt(partes[3]);
                    String dataLancamento = partes[4];

                    // Lê os participantes
                    String[] participantesLinha = reader.readLine().split(":")[1].split(",");
                    ArrayList<String> participantes = new ArrayList<>(List.of(participantesLinha));

                    Podcast podcast = new Podcast(nomePodcast, produtor, genero, duracao, dataLancamento, participantes);
                    podcasts.add(podcast);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar os podcasts: " + e.getMessage());
        }
        return podcasts;
    }


    private static void salvarPlaylists(List<Playlist> playlists) {
        try (PrintWriter writer = new PrintWriter(ARQUIVO_PLAYLISTS)) {
            for (Playlist playlist : playlists) {
                writer.println(playlist.getNome());


                List<Musica> musicas = playlist.getMusicas();
                for (Musica musica : musicas) {
                    writer.println(musica.getTitulo() + ";" + musica.getArtista() + ";" + musica.getDuracao());
                }


                writer.println("FIM_PLAYLIST");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar as playlists: " + e.getMessage());
        }
    }


    private static List<Playlist> carregarPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PLAYLISTS))) {
            String linha;
            Playlist playlistAtual = null;

            while ((linha = reader.readLine()) != null) {
                if (!linha.equals("FIM_PLAYLIST")) {
                    if (playlistAtual == null) {

                        playlistAtual = new Playlist(linha);
                    } else {

                        String[] partes = linha.split(";");
                        if (partes.length == 3) {
                            String titulo = partes[0];
                            String artista = partes[1];
                            int duracao = Integer.parseInt(partes[2]);

                            Musica musica = new Musica(titulo, artista, duracao);
                            playlistAtual.adicionarMusica(musica);
                        }
                    }
                } else {

                    if (playlistAtual != null) {
                        playlists.add(playlistAtual);
                        playlistAtual = null;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar as playlists: " + e.getMessage());
        }
        return playlists;
    }


}