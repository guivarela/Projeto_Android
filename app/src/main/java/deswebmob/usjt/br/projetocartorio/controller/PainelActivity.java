package deswebmob.usjt.br.projetocartorio.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import deswebmob.usjt.br.projetocartorio.R;
import deswebmob.usjt.br.projetocartorio.model.Atendimento;
import deswebmob.usjt.br.projetocartorio.model.ItemPainelAdapter;
import deswebmob.usjt.br.projetocartorio.model.Senha;
import deswebmob.usjt.br.projetocartorio.model.SenhaNetwork;
import deswebmob.usjt.br.projetocartorio.model.ItemPainel;

public class PainelActivity extends AppCompatActivity {
    private TextView tvStatus, tvCategoria, tvSenha, tvHoraGerada, tvPrevisaoInicio, tvAguandando;
    public static final String PAINEL_HOST = "http://169.254.181.176:8080/arqsw_sdesk_a4_solucao_parcial/rest/painel";
    ListView listView;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel);

        tvStatus = (TextView) findViewById(R.id.vStatus);
        tvCategoria = (TextView) findViewById(R.id.vCateg);
        tvSenha = (TextView) findViewById(R.id.vSenha);
        tvHoraGerada = (TextView) findViewById(R.id.vChegada);
        tvPrevisaoInicio = (TextView) findViewById(R.id.vTempoMedio);
        tvAguandando = (TextView) findViewById(R.id.vAguard);
        contexto = this;

        new DownloadPainel().execute(PAINEL_HOST);

    }

    private class DownloadPainel extends AsyncTask<String, Void, ItemPainelAdapter> {

        @Override
        protected ItemPainelAdapter doInBackground(String... strings) {
            ArrayList<ItemPainel> ip = new ArrayList<>();
            try {
                ip = SenhaNetwork.buscarPainel(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ItemPainelAdapter adapter = new ItemPainelAdapter(contexto, ip);
            return adapter;
        }

        protected void onPostExecute(ItemPainelAdapter adapter){
            listView = (ListView) findViewById(R.id.listaPainel);
            listView.setAdapter(adapter);
        }
    }
}

