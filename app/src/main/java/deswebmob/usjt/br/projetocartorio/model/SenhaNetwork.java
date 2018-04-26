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

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SenhaNetwork {

    public static boolean salvarSenha(String url, Senha senha){
        boolean success = false;
        // create your json here
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("statusSenha", senha.getStatusSenha());
            jsonObject.put("senha", senha.getSenha());
            jsonObject.put("categoria", senha.getCategoria());
            jsonObject.put("horaGerada", senha.getHoraGerada());

            JSONObject servico = new JSONObject();
            servico.put("id", senha.getServico().getId());
            servico.put("nomeServico", senha.getServico().getNomeServico());

            jsonObject.put("servico", servico);

            JSONObject subservico = new JSONObject();
            subservico.put("id", senha.getSubservico().getId());
            subservico.put("nomeSubservico", senha.getSubservico().getNomeSubservico());
            subservico.put("servico", servico);

            jsonObject.put("subservico",subservico);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            String resStr = response.body().string();
            success = true;
        } catch (IOException e) {
            success = false;
            e.printStackTrace();
        }
        return success;
    }

    public static ArrayList<Senha> buscarSenhas(String urlRest) throws IOException {
        ArrayList<Senha> senhas = getSenhas(urlRest);
        return senhas;
    }

    public static ArrayList<ItemPainel> buscarPainel(String urlRest) throws IOException {
        ArrayList<ItemPainel> ip = getPainel(urlRest);
        return ip;
    }

    public static ArrayList<Senha> getSenhas(String url) throws IOException {
        ArrayList<Senha> senhas = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();

        Log.println(Log.DEBUG,"url senhas" , url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        Log.println(Log.DEBUG,"json senhas" , json);

        try {
            JSONArray lista = new JSONArray(json);
            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject) lista.get(i);
                Senha s = new Senha();
                s.setId(item.getInt("idSenha"));
                s.setSenha(item.getString("senha"));
                s.setCategoria(item.getString("categoria"));
                s.setStatusSenha(item.getString("statusSenha"));
                s.setHoraGerada(item.getString("horaGerada"));
                JSONObject servico = item.getJSONObject("servico");
                JSONObject subservico = item.getJSONObject("subservico");
                Servico serv = new Servico(servico.getInt("id"), servico.getString("nomeServico"));
                Subservico subserv = new Subservico(subservico.getInt("id"), subservico.getString("nomeSubservico"), serv);
                s.setServico(serv);
                s.setSubservico(subserv);
                senhas.add(s);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        return senhas;

    }

    public static String getPrevisaoInicio(String url) throws IOException {
        String previsao;
        OkHttpClient client = new OkHttpClient();

        Log.println(Log.DEBUG,"url painel" , url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        previsao = response.body().string();

        return previsao;
    }
    public static String getPrevisaoFim(String url) throws IOException {
        String previsao;
        OkHttpClient client = new OkHttpClient();

        Log.println(Log.DEBUG,"url senhas" , url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        previsao = response.body().string();

        return previsao;
    }

    public static ArrayList<ItemPainel> getPainel(String url) throws IOException {
        ArrayList<ItemPainel> ip = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Log.println(Log.DEBUG, "url painel", url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();

        Log.println(Log.DEBUG, "json painel", json);

        try{
            JSONArray lista = new JSONArray(json);
            for (int i = 0; i < lista.length(); i++){
                JSONObject item = (JSONObject) lista.get(i);
                ItemPainel s = new ItemPainel();
                s.setId(item.getInt("idSenha"));
                s.setSenha(item.getString("senha"));
                s.setCategoria(item.getString("categoria"));
                s.setStatusSenha(item.getString("statusSenha"));
                s.setHoraGerada(item.getString("horaGerada"));
                JSONObject servico = item.getJSONObject("servico");
                JSONObject subservico = item.getJSONObject("subservico");
                Servico serv = new Servico(servico.getInt("id"), servico.getString("nomeServico"));
                Subservico subserv = new Subservico(subservico.getInt("id"), subservico.getString("nomeSubservico"), serv);
                s.setServico(serv);
                s.setSubservico(subserv);
                ip.add(s);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        return ip;
    }
}