package tcc.marcelo.com.br.sadp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.view.holder.PacienteHolder;

/**
 * Created by GATI on 27/09/2017.
 */

public class PacientesAdapter extends RecyclerView.Adapter<PacienteHolder> {

    private List<Paciente> pacientes = new ArrayList<>();

    public PacientesAdapter(List<Paciente> pacientes){
        this.pacientes = pacientes;
    }

    @Override
    public PacienteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PacienteHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.paciente_line_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(PacienteHolder holder, int position) {
        holder.setPaciente(pacientes.get(position));
    }

    @Override
    public int getItemCount() {
        return pacientes != null ? pacientes.size() : 0;
    }
}
