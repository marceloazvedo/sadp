package tcc.marcelo.com.br.sadp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.view.holder.PacienteHolder;

/**
 * Created by Marcelo S. de Azevedo on 27/09/2017.
 */
public class PacientesAdapter extends RecyclerView.Adapter<PacienteHolder> {

    private List<Paciente> pacientes = new ArrayList<>();
    private int selecionado;

    public PacientesAdapter(List<Paciente> pacientes){
        this.pacientes = pacientes;
        this.selecionado = -1;
    }

    @Override
    public PacienteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PacienteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.paciente_line_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(PacienteHolder holder, int position) {
        holder.setPaciente(pacientes.get(position));
        holder.setAdapter(this);
        holder.setSelecionado(selecionado == position);
    }

    @Override
    public int getItemCount() {
        return pacientes != null ? pacientes.size() : 0;
    }

    public int getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(int selecionado) {
        this.selecionado = selecionado;
    }

}
