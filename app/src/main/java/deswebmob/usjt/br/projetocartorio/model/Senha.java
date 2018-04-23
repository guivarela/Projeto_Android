package deswebmob.usjt.br.projetocartorio.model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by guivarela on 21/04/18.
 */

public class Senha implements Serializable {
    public final static String DATE_PATTERN = "HH:mm:ss";
    private int id;
    private String senha, statusSenha, categoria;
    private String horaGerada;
    private Servico servico;
    private Subservico subservico;

    public Senha(int id, String senha, String categoria, Servico servico, Subservico subservico) {
        this.id = id;
        this.senha = senha;
        this.categoria = categoria;
        this.servico = servico;
        this.subservico = subservico;
    }

    public Senha() {
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
        return "Senha{" +
                "id=" + id +
                ", senha='" + senha + '\'' +
                ", statusSenha='" + statusSenha + '\'' +
                ", categoria='" + categoria + '\'' +
                ", horaGerada=" + horaGerada +
                ", servico=" + servico +
                ", subservico=" + subservico +
                '}';
    }
}
