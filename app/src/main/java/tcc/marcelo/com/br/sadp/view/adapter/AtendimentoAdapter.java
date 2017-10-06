package tcc.marcelo.com.br.sadp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Consulta;
import tcc.marcelo.com.br.sadp.view.holder.AtendimentoHolder;
import tcc.marcelo.com.br.sadp.view.holder.PacienteHolder;

/**
 * Created by Marcelo S. de Azevedo on 06/10/2017.
 */
public class AtendimentoAdapter extends RecyclerView.Adapter<AtendimentoHolder> {

    private List<Consulta> consultas = new ArrayList<>();

    public AtendimentoAdapter(List<Consulta> consultas){
        this.consultas = consultas;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    @Override
    public AtendimentoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AtendimentoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.atendimento_line_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(AtendimentoHolder holder, int position) {
        holder.setConsulta(consultas.get(position));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return consultas != null ? consultas.size() : 0;
    }

}
