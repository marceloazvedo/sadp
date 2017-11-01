package tcc.marcelo.com.br.sadp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Sintoma;
import tcc.marcelo.com.br.sadp.view.holder.SintomaHolder;

/**
 * Created by marcelo on 10/10/17.
 */
public class SintomaAdapter extends RecyclerView.Adapter<SintomaHolder> {

    private List<Sintoma> sintomas = new ArrayList<>();

    public SintomaAdapter(List<Sintoma> sintomas) {
        this.sintomas = sintomas;
    }

    @Override
    public SintomaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SintomaHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sintoma_line_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(SintomaHolder holder, int position) {
        holder.setSintoma(sintomas.get(position));
    }

    @Override
    public int getItemCount() {
        return sintomas != null ? sintomas.size() : 0;
    }
}
