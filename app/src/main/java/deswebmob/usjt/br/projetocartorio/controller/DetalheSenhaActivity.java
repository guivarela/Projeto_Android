package deswebmob.usjt.br.projetocartorio.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import deswebmob.usjt.br.projetocartorio.R;
import deswebmob.usjt.br.projetocartorio.model.Senha;

public class DetalheSenhaActivity extends AppCompatActivity {
    private TextView tvServico, tvCategoria, tvSenha, tvHoraGerada, tvPrevisaoInicio, tvPrevisaoFim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_senha);
        Intent intent = getIntent();
        Senha senha = (Senha)intent.getSerializableExtra("senha");

        tvServico = (TextView) findViewById(R.id.tv_servico1);
        tvCategoria = (TextView) findViewById(R.id.tv_categoria);
        tvSenha = (TextView) findViewById(R.id.tv_senha);
        tvHoraGerada = (TextView) findViewById(R.id.tv_horaGerada);
        tvPrevisaoInicio = (TextView) findViewById(R.id.tv_previsaoInicio);
        tvPrevisaoFim = (TextView) findViewById(R.id.tv_previsaoFim);

        tvServico.setText(senha.getServico().getNomeServico());
        tvSenha.setText(senha.getSenha());
        tvCategoria.setText(senha.getCategoria());
        tvHoraGerada.setText(senha.getHoraGerada());
        tvPrevisaoInicio.setText("00:00:00");
        tvPrevisaoFim.setText("00:00:00");
    }
}
