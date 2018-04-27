package deswebmob.usjt.br.projetocartorio.model;

import java.io.Serializable;

public class Tempo implements Serializable {
    private int id;
    private Subservico subservico;
    private String tempoMedio;

    public Tempo(int id, Subservico subservico, String tempoMedio) {
        this.id = id;
        this.subservico = subservico;
        this.tempoMedio = tempoMedio;
    }

    public Tempo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subservico getSubservico() {
        return subservico;
    }

    public void setSubservico(Subservico subservico) {
        this.subservico = subservico;
    }

    public String getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(String tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    @Override
    public String toString() {
        return "Tempo{" +
                "id=" + id +
                ", subservico=" + subservico +
                ", tempoMedio='" + tempoMedio + '\'' +
                '}';
    }
}
