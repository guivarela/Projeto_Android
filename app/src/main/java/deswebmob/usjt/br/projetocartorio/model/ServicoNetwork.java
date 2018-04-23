package deswebmob.usjt.br.projetocartorio.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServicoNetwork {

    public static ArrayList<Servico> buscarServicos(String urlRest) throws IOException {
        ArrayList<Servico> servicos = getServicos(urlRest);
        return servicos;
    }

    public static ArrayList<Servico> getServicos(String url) throws IOException {
        ArrayList<Servico> servicos = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();

        Log.println(Log.DEBUG,"url servicos" , url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        Log.println(Log.DEBUG,"json servicos" , json);

        try {
            JSONArray lista = new JSONArray(json);
            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject) lista.get(i);
                Servico servico = new Servico();
                servico.setId(item.getInt("id"));
                servico.setNomeServico(item.getString("nomeServico"));
                servicos.add(servico);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        return servicos;

    }
}