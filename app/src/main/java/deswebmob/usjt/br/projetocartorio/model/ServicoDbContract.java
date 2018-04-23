package deswebmob.usjt.br.projetocartorio.model;

import android.provider.BaseColumns;

public final class ServicoDbContract {
    public ServicoDbContract(){

    }

    public static abstract class ServicoBanco implements BaseColumns {
        public static final String TABLE_NAME = "Servico";
        public static final String ID = "id";
        public static final String NOME_SERVICO = "nomeServico";
    }
}
