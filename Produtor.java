
import java.util.ArrayList;

// Classe específica para o produtor
class Produtor extends Usuario {
    private String nomeProfissional; // Nome profissional do podcast

    // Construtor
    public Produtor(String nome, int idade, String cpf, String nacionalidade, String senha, String nomeProfissional) {
        super(nome, idade, cpf, nacionalidade, senha);
        this.nomeProfissional = nomeProfissional;
    }

    // Getters e Setters
    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    // Método para criar podcast
    public void criarPodcast(String nomePodcast, ArrayList<String> participantes) {
        // Lógica para criar podcast
        System.out.println("Podcast criado pelo produtor: " + this.nomeProfissional);
        System.out.println("Nome do Podcast: " + nomePodcast);
        System.out.println("Participantes: " + participantes);
    }
}