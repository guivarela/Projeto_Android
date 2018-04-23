package deswebmob.usjt.br.projetocartorio.model;

import android.provider.BaseColumns;

public final class SenhaDbContract {
    public SenhaDbContract(){

    }

    public static abstract class SenhaBanco implements BaseColumns {
        public static final String TABLE_NAME = "Senha";
        public static final String ID = "id";
        public static final String STATUS_SENHA = "statusSenha";
        public static final String CATEGORIA = "categoria";
        public static final String SENHA = "senha";
        public static final String SERVICO_ID = "servicoId";
        public static final String SUBSERVICO_ID = "subservicoId";
        public static final String HORA_GERADA = "horaGerada";
    }
}
