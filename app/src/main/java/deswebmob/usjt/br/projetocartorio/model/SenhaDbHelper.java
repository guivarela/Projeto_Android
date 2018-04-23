package deswebmob.usjt.br.projetocartorio.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static deswebmob.usjt.br.projetocartorio.model.SenhaDbContract.SenhaBanco;

public class SenhaDbHelper extends SQLiteOpenHelper {
    public static final String SQL_CREATE_SENHA =
            "CREATE TABLE " + SenhaBanco.TABLE_NAME + " ( " +
                    SenhaBanco._ID + " INTEGER PRIMARY KEY, " +
                    SenhaBanco.ID + " INTEGER, " +
                    SenhaBanco.STATUS_SENHA + " TEXT, " +
                    SenhaBanco.CATEGORIA + " TEXT, " +
                    SenhaBanco.SENHA + " TEXT," +
                    SenhaBanco.SERVICO_ID + " INTEGER, " +
                    SenhaBanco.SUBSERVICO_ID + " INTEGER," +
                    SenhaBanco.HORA_GERADA + " INTEGER ) ";

    public static final String SQL_DROP_SENHA =
            "DROP TABLE IF EXISTS " + SenhaBanco.TABLE_NAME;

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Senha.db";

    public SenhaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_SENHA);
        Log.println(Log.DEBUG,"SenhaDbHelper" , "OnCreate: criou a tabela Senha com o comando " + SQL_CREATE_SENHA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_SENHA);
        Log.println(Log.DEBUG,"SenhaDbHelper" , "OnUpgrade: dropou a tabela Senha com o comando " + SQL_DROP_SENHA);
        db.execSQL(SQL_CREATE_SENHA);
        Log.println(Log.DEBUG,"SenhaDbHelper" , "OnUpgrade: criou a tabela Senha com o comando " + SQL_CREATE_SENHA);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_SENHA);
        Log.println(Log.DEBUG,"SenhaDbHelper" , "onDowngrade: dropou a tabela Senha com o comando " + SQL_DROP_SENHA);
        db.execSQL(SQL_CREATE_SENHA);
        Log.println(Log.DEBUG,"SenhaDbHelper" , "onDowngrade: criou a tabela Senha com o comando " + SQL_CREATE_SENHA);
    }
}
