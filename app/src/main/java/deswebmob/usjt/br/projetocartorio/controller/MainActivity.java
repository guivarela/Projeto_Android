package deswebmob.usjt.br.projetocartorio.controller;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import deswebmob.usjt.br.projetocartorio.R;
import deswebmob.usjt.br.projetocartorio.model.Senha;
import deswebmob.usjt.br.projetocartorio.model.SenhaDb;
import deswebmob.usjt.br.projetocartorio.model.SenhaNetwork;
import deswebmob.usjt.br.projetocartorio.model.Servico;
import deswebmob.usjt.br.projetocartorio.model.ServicoNetwork;

public class MainActivity extends AppCompatActivity {
    public static final String SENHAS = "deswebmob.usjt.br.projetocartorio.senhas";
    public static final String PROJ_HOST = "http://192.168.1.5:8080/arqsw_sdesk_a4_solucao_parcial/";
    public static final String SALVAR_SENHA_HOST = "http://192.168.1.5:8080/arqsw_sdesk_a4_solucao_parcial/rest/senhas";
    private RadioGroup radioGroupCategoria;
    private RadioButton radioCategoria;
    public String senha;
    public Button bt_gerarSenha;
    private Senha ultimaSenha;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        contexto = this;

        SenhaDb db = new SenhaDb(contexto);
        new DownloadJsonServicos().execute(PROJ_HOST + "rest/servicos");
    }

    private class DownloadJsonSenhas extends AsyncTask<String, Void, ArrayList<Senha>> {

        @Override
        protected ArrayList<Senha> doInBackground(String... strings) {
            ArrayList<Senha> senhas = new ArrayList<>();
            try {
                senhas = SenhaNetwork.buscarSenhas(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return senhas;
        }

        protected void onPostExecute(ArrayList<Senha> senhas){
            ultimaSenha = senhas.get(senhas.size()-1);
        }
    }

    private class DownloadJsonServicos extends AsyncTask<String, Void, ArrayList<Servico>> {

        @Override
        protected ArrayList<Servico> doInBackground(String... strings) {
            ArrayList<Servico> servicos = new ArrayList<>();
            try {
                servicos = ServicoNetwork.buscarServicos(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return servicos;
        }

        protected void onPostExecute(ArrayList<Servico> servicos){
            populateSpinner(servicos);
        }
    }

    private void populateSpinner(final List<Servico> servicos){
        ArrayList<String>servicoName = new ArrayList<>();
        final Spinner servicoSpinner = (Spinner) findViewById(R.id.servicoSpinner);

        for(Servico s:servicos){
            servicoName.add(s.getNomeServico());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, servicoName);
        servicoSpinner.setAdapter(adapter);

        servicoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                String nomeServico = servicoSpinner.getSelectedItem().toString();
                int id = servicos.get(position).getId();

                new DownloadJsonSenhas().execute(PROJ_HOST + "rest/senhas/"+id+"");
                bt_gerarSenha = (Button) findViewById(R.id.bt_gerarSenha);
                bt_gerarSenha.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        Senha senha123 = gerarSenha(ultimaSenha);

                        Intent intent = new Intent(contexto, DetalheSenhaActivity.class);
                        intent.putExtra("senha", senha123);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private Senha gerarSenha(Senha senha){
        radioGroupCategoria = (RadioGroup) findViewById(R.id.radioCategoria);
        int selected = radioGroupCategoria.getCheckedRadioButtonId();
        radioCategoria = (RadioButton) findViewById(selected);
        senha.setCategoria(radioCategoria.getText().toString());
        Toast.makeText(contexto,
        radioCategoria.getText(), Toast.LENGTH_SHORT).show();

        boolean success = false;
        Senha s = senha;
        EditText etSenha = (EditText) findViewById(R.id.et_senha);
        etSenha.setEnabled(false);
        etSenha.setInputType(InputType.TYPE_NULL);
        String novaSenha = "";
        int x = Integer.parseInt(s.getSenha().substring(s.getSenha().length() - 1));
        int y = Integer.parseInt(s.getSenha().substring(s.getSenha().length() - 2, s.getSenha().length() - 1));
        int z = Integer.parseInt(s.getSenha().substring(s.getSenha().length() - 3, s.getSenha().length() - 2));
        String sigla = s.getSenha().substring(0, 2);

        if(x == 9) {
            if(y == 9) {
                if(z != 9) {
                    x = 0;
                    y = 0;
                    z += 1;
                    novaSenha = z + "" +  y + "" + x;
                }
                if(z == 9) {
                    x = 0;
                    y = 0;
                    z = 0;
                    novaSenha = z + "" +  y + "" + x;
                }
            }else {
                x = 0;
                y += 1;
                novaSenha = z + "" +  y + "" + x;
            }
        }else {
            x += 1;
            novaSenha = z + "" +  y + "" + x;
        }
        novaSenha = sigla + novaSenha;
        s.setId(s.getId()+1);
        s.setSenha(novaSenha);
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        String data = df.format(Calendar.getInstance().getTime());
        s.setHoraGerada(data);
        SenhaDb senhaDb = new SenhaDb(contexto);
        if(!SenhaNetwork.salvarSenha(SALVAR_SENHA_HOST, s)){
            Toast toast = Toast.makeText(contexto, "Algo deu errado tente novamente",Toast.LENGTH_SHORT);
            toast.show();
        }
        return senha;
    }
}
