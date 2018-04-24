package deswebmob.usjt.br.projetocartorio.model;

public class Atendimento {
    String pInicio, pFim;

    public Atendimento(String pInicio, String pFim) {
        this.pInicio = pInicio;
        this.pFim = pFim;
    }

    public Atendimento(){

    }

    public String getpInicio() {
        return pInicio;
    }

    public void setpInicio(String pInicio) {
        this.pInicio = pInicio;
    }

    public String getpFim() {
        return pFim;
    }

    public void setpFim(String pFim) {
        this.pFim = pFim;
    }

    @Override
    public String toString() {
        return "Atendimento{" +
                "pInicio='" + pInicio + '\'' +
                ", pFim='" + pFim + '\'' +
                '}';
    }
}
