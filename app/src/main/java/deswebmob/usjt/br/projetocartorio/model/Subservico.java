package deswebmob.usjt.br.projetocartorio.model;

import java.io.Serializable;

/**
 * Created by Guilherme Varela, RA: 81613746.
 */

public class Subservico implements Serializable {
    private int id;
    private String nomeSubservico;
    private Servico servico;

    public Subservico(int id, String nomeSubservico, Servico servico) {
        this.id = id;
        this.nomeSubservico = nomeSubservico;
        this.servico = servico;
    }

    public Subservico() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeSubservico() {
        return nomeSubservico;
    }

    public void setNomeSubservico(String nomeSubservico) {
        this.nomeSubservico = nomeSubservico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    @Override
    public String toString() {
        return "Subservico [id=" + id + ", nomeSubservico=" + nomeSubservico + ", servico=" + servico + "]";
    }
}
