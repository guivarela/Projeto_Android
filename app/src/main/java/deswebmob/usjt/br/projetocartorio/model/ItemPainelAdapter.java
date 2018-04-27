package deswebmob.usjt.br.projetocartorio.model;

import android.content.ClipData;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import deswebmob.usjt.br.projetocartorio.R;
import deswebmob.usjt.br.projetocartorio.controller.MainActivity;

public class ItemPainelAdapter extends BaseAdapter{
    public TextView tvStatus, tvCategoria, tvSenha, tvHoraGerada, tvPrevisaoInicio, tvAguandando;
    private ViewHolder viewHolder;
    public static final String PROJ_HOST = "http://169.254.181.176:8080/arqsw_sdesk_a4_solucao_parcial/";
    Context contexto;
    ArrayList<ItemPainel> ip;
    ArrayList<Tempo> tempos;

    public ItemPainelAdapter(Context contexto, ArrayList<ItemPainel> ip, ArrayList<Tempo> tempos) {
        this.ip = ip;
        this.contexto = contexto;
        this.tempos = tempos;

    }
    @Override
    public int getCount() {
        return ip.size();
    }

    @Override
    public Object getItem(int position) {
        return ip.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_painel, parent, false);

            tvStatus = (TextView) view.findViewById(R.id.vStatus);
            tvCategoria = (TextView) view.findViewById(R.id.vCateg);
            tvSenha = (TextView) view.findViewById(R.id.vSenha);
            tvHoraGerada = (TextView) view.findViewById(R.id.vChegada);
            tvPrevisaoInicio = (TextView) view.findViewById(R.id.vTempoMedio);
            tvAguandando = (TextView) view.findViewById(R.id.vAguard);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.setStatus(tvStatus);
            viewHolder.setCategoria(tvCategoria);
            viewHolder.setSenha(tvSenha);
            viewHolder.setHoraGerada(tvHoraGerada);
            viewHolder.setPrevisaoAt(tvPrevisaoInicio);
            view.setTag(viewHolder);
        }


        ItemPainel item = ip.get(position);
        for(Tempo t:tempos){
            if (item.getSubservico().getId() == t.getSubservico().getId()){
                item.setTempoMedio(t.getTempoMedio());
            }
        }
        viewHolder = (ViewHolder) view.getTag();

        viewHolder.getStatus().setText(item.getStatusSenha());
        viewHolder.getCategoria().setText(item.getCategoria()+"/");
        viewHolder.getSenha().setText(item.getSenha());
        viewHolder.getHoraGerada().setText(item.getHoraGerada());
        viewHolder.getPrevisaoAt().setText(item.getTempoMedio());
        return view;
    }
//    private DownloadPrevisaoInicio(String url){
//        String aa = "";
//        aa = SenhaNetwork.getPrevisaoInicio(url);
//        return viewHolder.getPrevisaoAt().setText(aa);
//
//    }
}
