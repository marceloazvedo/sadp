package tcc.marcelo.com.br.sadp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.dto.EsquizofreniaDiagnosticoDTO;
import tcc.marcelo.com.br.sadp.view.holder.EsquizofreniaDiagnosticoHolder;

/**
 * Created by marcelo on 05/11/17.
 */
public class EsquizofreniaAdapter extends RecyclerView.Adapter<EsquizofreniaDiagnosticoHolder> {

    private List<EsquizofreniaDiagnosticoDTO> esquizofrenias;

    public EsquizofreniaAdapter(List<EsquizofreniaDiagnosticoDTO> esquizofrenias){
        this.esquizofrenias = esquizofrenias;
    }

    @Override
    public EsquizofreniaDiagnosticoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EsquizofreniaDiagnosticoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.diagnostico_line_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(EsquizofreniaDiagnosticoHolder holder, int position) {
        holder.setEsquizofrenia(esquizofrenias.get(position));
    }

    @Override
    public int getItemCount() {
        return esquizofrenias != null ? esquizofrenias.size() : 0;
    }
}
