
import java.util.ArrayList;

// Classe para o podcast
class Podcast {
    private String nomePodcast;
    private String produtor;
    private String genero;
    private int duracao; // Em segundos
    private String dataLancamento;
    private ArrayList<String> participantes;

    // Construtor
    public Podcast(String nomePodcast, String produtor, String genero, int duracao, String dataLancamento, ArrayList<String> participantes) {
        this.nomePodcast = nomePodcast;
        this.produtor = produtor;
        this.genero = genero;
        this.duracao = duracao;
        this.dataLancamento = dataLancamento;
        this.participantes = participantes;
    }

    // Getters e Setters
    public String getNomePodcast() {
        return nomePodcast;
    }

    public void setNomePodcast(String nomePodcast) {
        this.nomePodcast = nomePodcast;
    }

    public String getProdutor() {
        return produtor;
    }

    public void setProdutor(String produtor) {
        this.produtor = produtor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public ArrayList<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<String> participantes) {
        this.participantes = participantes;
    }
}