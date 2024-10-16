import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String ARQUIVO_USUARIOS = "usuarios.txt";
    private static final String ARQUIVO_MUSICAS = "musicas.txt";
    private static final String ARQUIVO_PODCASTS = "podcasts.txt";
    private static final String ARQUIVO_PLAYLISTS = "playlists.txt";
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Carrega os dados dos arquivos de log
        List<Usuario> usuarios = carregarUsuarios();
        List<Musica> musicas = carregarMusicas();
        List<Podcast> podcasts = carregarPodcasts();
        List<Playlist> playlists = carregarPlaylists();

        System.out.println(usuarios);
        
        // Loop principal do programa
        while (true) {
            // Exibe menu de opções para o usuário
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
                    cadastrarUsuario(usuarios, scanner, null);
                    salvarUsuarios(usuarios);
                }
                case 2 -> // Login de usuário
                    fazerLogin(usuarios, scanner);
                case 3 -> {
                    // Sair do programa
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    salvarUsuarios(usuarios);
                    salvarMusicas(musicas);
                    salvarPodcasts(podcasts);
                    salvarPlaylists(playlists);
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }


    
    // Método cadastrarUsuario
    private static void cadastrarUsuario(List<Usuario> usuarios, Scanner scanner, Usuario usuario) {
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

        Usuario novoUsuario = null;

        switch (tipoUsuario) {
            case 1 -> {
                // Cadastrar um artista
                System.out.print("Nome Artístico: ");
                String nomeArtistico = scanner.nextLine();

                System.out.print("Nome do Empresário: ");
                String nomeEmpresario = scanner.nextLine();

                novoUsuario = new Artista(nome, idade, cpf, nacionalidade, senha, nomeArtistico, nomeEmpresario);
            }
            case 2 -> {
                // Cadastrar um produtor
                System.out.print("Nome Profissional: ");
                String nomeProfissional = scanner.nextLine();

                usuario = new Produtor(nome, idade, cpf, nacionalidade, senha, nomeProfissional);
            }
            case 3 -> {
                // Cadastrar um ouvinte
                System.out.print("Nome de Exibição: ");
                String nomeExibicao = scanner.nextLine();

                usuario = new Ouvinte(nome, idade, cpf, nacionalidade, senha, nomeExibicao);
            }
            default -> System.out.println("Opção inválida!");
        }

        if (novoUsuario != null) {
            usuarios.add(novoUsuario);
            System.out.println("Usuário cadastrado com sucesso!");
            return;
        }
        
        throw new UnsupportedOperationException("Não temos essa implementação ainda.");
    }

    // Método fazerLogin
    private static void fazerLogin(List<Usuario> usuarios, Scanner scanner) {
        System.out.println("------------- LOGIN -------------");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        // esse valor boleano será utilizado para direcionar ao sistema
        boolean usuarioEncontrado = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)) {
                usuarioEncontrado = true;
                System.out.println("Login realizado com sucesso!");

                // Aqui você pode direcionar o usuário para a tela principal do sistema,
                // de acordo com o seu tipo (artista, produtor ou ouvinte).
                System.out.println("Bem-vindo, " + usuario.getNome() + "!");
                // ...
                break;
            }
            else {
                System.out.println("Usuário ou senha inválidos!");
                return;
                }
            }
        }

    // Método para salvar os usuários em um arquivo
    private static void salvarUsuarios(List<Usuario> usuarios) {
        try (PrintWriter writer = new PrintWriter(ARQUIVO_USUARIOS)) {
            for (Usuario usuario : usuarios) {
                // Salva as informações do usuário no arquivo
                writer.println(usuario.getClass().getSimpleName() + ";" + usuario.getNome() + ";" + usuario.getIdade() + ";" + usuario.getCpf() + ";" + usuario.getNacionalidade() + ";" + usuario.getSenha());

                // Salva as informações adicionais de acordo com o tipo de usuário
                if (usuario instanceof Artista) {
                    Artista artista = (Artista) usuario;
                    writer.println("Nome Artístico:" + artista.getNomeArtistico());
                    writer.println("Nome do Empresário:" + artista.getNomeEmpresario());
                } else if (usuario instanceof Produtor produtor) {
                    writer.println("Nome Profissional:" + produtor.getNomeProfissional());
                } else if (usuario instanceof Ouvinte ouvinte) {
                    writer.println("Nome de Exibição:" + ouvinte.getNomeExibicao());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao salvar os usuários: " + e.getMessage());
        }
    }

    // Método para carregar os usuários do arquivo
    private static List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
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

                    Usuario usuario = null;

                    switch (tipo) {
                        case "Artista" -> {
                            String nomeArtistico = reader.readLine().split(":")[1];
                            String nomeEmpresario = reader.readLine().split(":")[1];
                            usuario = new Artista(nome, idade, cpf, nacionalidade, senha, nomeArtistico, nomeEmpresario);
                        }
                        case "Produtor" -> {
                            String nomeProfissional = reader.readLine().split(":")[1];
                            usuario = new Produtor(nome, idade, cpf, nacionalidade, senha, nomeProfissional);
                        }
                        case "Ouvinte" -> {
                            String nomeExibicao = reader.readLine().split(":")[1];
                            usuario = new Ouvinte(nome, idade, cpf, nacionalidade, senha, nomeExibicao);
                        }
                    }

                    if (usuario != null) {
                        usuarios.add(usuario);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar os usuários: " + e.getMessage());
        }
        return usuarios;
    }
 
    // Métodos para salvar e carregar músicas, podcasts e playlists
    private static void salvarMusicas(List<Musica> musicas) {
        // ...
    }

    private static List<Musica> carregarMusicas() {
        return null;
        // ...
    }

    private static void salvarPodcasts(List<Podcast> podcasts) {
        // ...
    }

    private static List<Podcast> carregarPodcasts() {
        // ...
        return null;
        // ...
    }

    private static void salvarPlaylists(List<Playlist> playlists) {
        // ...
    }

    private static List<Playlist> carregarPlaylists() {
        return null;
        // ...
    }


}