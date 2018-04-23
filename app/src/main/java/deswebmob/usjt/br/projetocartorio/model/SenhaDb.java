package deswebmob.usjt.br.projetocartorio.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import static deswebmob.usjt.br.projetocartorio.model.SenhaDbContract.SenhaBanco;
import static deswebmob.usjt.br.projetocartorio.model.ServicoDbContract.ServicoBanco;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SenhaDb {
    private SenhaDbHelper dbHelper;

    public SenhaDb(Context context) {
        dbHelper = new SenhaDbHelper(context);
    }

    public void inserirSenha(Senha senha) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SenhaBanco.ID, senha.getId());
        values.put(SenhaBanco.SENHA, senha.getSenha());
        values.put(SenhaBanco.CATEGORIA, senha.getCategoria());
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        String data = df.format(Calendar.getInstance().getTime());
        values.put(SenhaBanco.HORA_GERADA, data);
        values.put(SenhaBanco.STATUS_SENHA, "aberto");

        db.insert(SenhaBanco.TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<String> listarServicos() {
        ArrayList<String> servicos = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = {ServicoBanco.ID, ServicoBanco.NOME_SERVICO};

        String orderBy = ServicoBanco.ID;

        Cursor c;

        c = db.query(ServicoBanco.TABLE_NAME, colunas, null,
                null, null, null, orderBy);

        while (c.moveToNext()){
            Servico servico = new Servico();
            servico.setId(c.getInt(c.getColumnIndex(ServicoBanco.ID)));
            servico.setNomeServico(c.getString(c.getColumnIndex(ServicoBanco.NOME_SERVICO)));
            servicos.add(servico.getNomeServico());
        }
        c.close();
        db.close();
        return servicos;
    }
}
