package deswebmob.usjt.br.projetocartorio.model;

public class ItemPainel {
    public final static String DATE_PATTERN = "HH:mm:ss";
    private int id;
    private String senha, statusSenha, categoria;
    private String horaGerada, tempoMedio, aguardando;
    private Servico servico;
    private Subservico subservico;

    public ItemPainel(int id, String senha, String statusSenha, String categoria, String horaGerada, String tempoMedio, String aguardando, Servico servico, Subservico subservico) {
        this.id = id;
        this.senha = senha;
        this.statusSenha = statusSenha;
        this.categoria = categoria;
        this.horaGerada = horaGerada;
        this.tempoMedio = tempoMedio;
        this.aguardando = aguardando;
        this.servico = servico;
        this.subservico = subservico;
    }

    public ItemPainel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatusSenha() {
        return statusSenha;
    }

    public void setStatusSenha(String statusSenha) {
        this.statusSenha = statusSenha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getHoraGerada() {
        return horaGerada;
    }

    public void setHoraGerada(String horaGerada) {
        this.horaGerada = horaGerada;
    }

    public String getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(String tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public String getAguardando() {
        return aguardando;
    }

    public void setAguardando(String aguardando) {
        this.aguardando = aguardando;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Subservico getSubservico() {
        return subservico;
    }

    public void setSubservico(Subservico subservico) {
        this.subservico = subservico;
    }

    @Override
    public String toString() {
        return "ItemPainel{" +
                "id=" + id +
                ", senha='" + senha + '\'' +
                ", statusSenha='" + statusSenha + '\'' +
                ", categoria='" + categoria + '\'' +
                ", horaGerada='" + horaGerada + '\'' +
                ", tempoMedio='" + tempoMedio + '\'' +
                ", aguardando='" + aguardando + '\'' +
                ", servico=" + servico +
                ", subservico=" + subservico +
                '}';
    }
}
