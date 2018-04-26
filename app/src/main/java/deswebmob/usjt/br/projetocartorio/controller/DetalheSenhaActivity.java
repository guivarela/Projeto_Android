package deswebmob.usjt.br.projetocartorio.controller;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import deswebmob.usjt.br.projetocartorio.R;
import deswebmob.usjt.br.projetocartorio.model.Atendimento;
import deswebmob.usjt.br.projetocartorio.model.Senha;
import deswebmob.usjt.br.projetocartorio.model.SenhaNetwork;

public class DetalheSenhaActivity extends AppCompatActivity {
    private TextView tvServico, tvCategoria, tvSenha, tvHoraGerada, tvPrevisaoInicio, tvPrevisaoFim;
    private Button btIrPainel;
    private Context contexto;
    public static final String PREVISAO_HOST = "http://169.254.181.176:8080/arqsw_sdesk_a4_solucao_parcial/rest/previsao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_senha);
        Intent intent = getIntent();
        Senha senha = (Senha)intent.getSerializableExtra("senha");
        contexto = this;

        new DownloadPrevisaoInicio().execute(PREVISAO_HOST + "Inicio/"+senha.getSubservico().getId());
        new DownloadPrevisaoFim().execute(PREVISAO_HOST + "Fim/"+senha.getSubservico().getId());
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

        btIrPainel = (Button) findViewById(R.id.bt_irPainel);
        btIrPainel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(contexto, PainelActivity.class);
                startActivity(intent);
            }
        });
    }


    private class DownloadPrevisaoInicio extends AsyncTask<String, Void, Atendimento> {

        Atendimento atendimento = new Atendimento();
        @Override
        protected Atendimento doInBackground(String... strings) {
            try {
                atendimento.setpInicio(SenhaNetwork.getPrevisaoInicio(strings[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return atendimento;
        }

        protected void onPostExecute(Atendimento atendimento){
            tvPrevisaoInicio.setText(atendimento.getpInicio());
        }
    }


    private class DownloadPrevisaoFim extends AsyncTask<String, Void, Atendimento> {

        Atendimento atendimento = new Atendimento();
        @Override
        protected Atendimento doInBackground(String... strings) {
            try {
                atendimento.setpFim(SenhaNetwork.getPrevisaoFim(strings[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return atendimento;
        }

        protected void onPostExecute(Atendimento atendimento){
            tvPrevisaoFim.setText(atendimento.getpFim());
        }
    }
}
