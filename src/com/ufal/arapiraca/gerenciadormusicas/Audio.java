package com.ufal.arapiraca.gerenciadormusicas;

public class Audio {
    private int duracao;
    private int faixaEtariaMinima;

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    public void setFaixaEtariaMinima(int faixaEtariaMinima) {
        this.faixaEtariaMinima = faixaEtariaMinima;
    }
    public int getFaixaEtariaMinima() {
        return faixaEtariaMinima;
    }
    public int getDuracao() {
        return duracao;
    }
    
}
