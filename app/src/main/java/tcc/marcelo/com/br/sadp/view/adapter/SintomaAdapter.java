package tcc.marcelo.com.br.sadp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.enums.TipoSintomaAdapter;
import tcc.marcelo.com.br.sadp.model.Sintoma;
import tcc.marcelo.com.br.sadp.view.holder.SintomaHolder;

/**
 * Created by marcelo on 10/10/17.
 */
public class SintomaAdapter extends RecyclerView.Adapter<SintomaHolder> {

    private List<Sintoma> sintomas;
    private TipoSintomaAdapter tipoAdapter;

    public SintomaAdapter(List<Sintoma> sintomas, TipoSintomaAdapter tipoSintomaAdapter) {
        this.sintomas = sintomas;
        this.tipoAdapter = tipoSintomaAdapter;
    }

    @Override
    public SintomaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SintomaHolder sintomaHolder = new SintomaHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sintoma_line_recycler, parent, false), this);
        return sintomaHolder;
    }

    @Override
    public void onBindViewHolder(SintomaHolder holder, int position) {
        holder.setSintoma(sintomas.get(position));
        holder.setAdapter(this);
    }

    @Override
    public int getItemCount() {
        return sintomas != null ? sintomas.size() : 0;
    }

    public void add(Sintoma sintoma) {
        sintomas.add(sintoma);
        notifyItemInserted(sintomas.size() - 1);
        notifyItemRangeChanged(sintomas.size() - 1, sintomas.size());
    }

    public void remove(int adapterPosition) {
        Sintoma s = sintomas.get(adapterPosition);
        sintomas.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
        notifyItemRangeChanged(adapterPosition, sintomas.size());
    }

    public int getAdapterPosition(Sintoma s){
        for(int i=0;i<sintomas.size();i++){
            if(s.equals(sintomas.get(i))){
                return i;
            }
        }
        return -1;
    }

    public List<Sintoma> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<Sintoma> sintomas) {
        this.sintomas = sintomas;
    }

    public TipoSintomaAdapter getTipoAdapter() {
        return tipoAdapter;
    }

    public void setTipoAdapter(TipoSintomaAdapter tipoAdapter) {
        this.tipoAdapter = tipoAdapter;
    }
}
