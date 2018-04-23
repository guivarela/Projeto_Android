package deswebmob.usjt.br.projetocartorio.model;


import java.io.Serializable;

/**
 * Created by Guilherme Varela, RA: 81613746.
 */

public class Servico implements Serializable {
    private int id;
    private String nomeServico;

    public Servico(int id, String nomeServico) {
        this.id = id;
        this.nomeServico = nomeServico;
    }

    public Servico() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", nomeServico='" + nomeServico + '\'' +
                '}';
    }
}
