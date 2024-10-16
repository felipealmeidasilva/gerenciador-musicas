
import java.util.ArrayList;
import java.util.List;

// Classe para a playlist
class Playlist {
    private String nome;
    private final List<Musica> musicas;

    // Construtor
    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    // Método para adicionar música à playlist
    public void adicionarMusica(Musica musica) {
        musicas.add(musica);
    }

    // Método para remover música da playlist
    public void removerMusica(Musica musica) {
        musicas.remove(musica);
    }
}