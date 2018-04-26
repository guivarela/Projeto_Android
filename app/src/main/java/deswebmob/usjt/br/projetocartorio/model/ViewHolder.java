package deswebmob.usjt.br.projetocartorio.model;

import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewHolder {
    private TextView senha, status, categoria, horaGerada, previsaoAt;

    public TextView getSenha() {
        return senha;
    }

    public void setSenha(TextView senha) {
        this.senha = senha;
    }

    public TextView getStatus() {
        return status;
    }

    public void setStatus(TextView status) {
        this.status = status;
    }

    public TextView getCategoria() {
        return categoria;
    }

    public void setCategoria(TextView categoria) {
        this.categoria = categoria;
    }

    public TextView getHoraGerada() {
        return horaGerada;
    }

    public void setHoraGerada(TextView horaGerada) {
        this.horaGerada = horaGerada;
    }

    public TextView getPrevisaoAt() {
        return previsaoAt;
    }

    public void setPrevisaoAt(TextView previsaoAt) {
        this.previsaoAt = previsaoAt;
    }
}
