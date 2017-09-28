package tcc.marcelo.com.br.sadp.view.holder;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Paciente;

/**
 * Created by Marcelo S. de Azevedo on 27/09/2017.
 */
public class PacienteHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    private Paciente paciente;
    private TextView nomePaciente;
    private TextView dataEntrada;
    private TextView dataUltimoAtendimento;

    public PacienteHolder(View itemView){
        super(itemView);
        nomePaciente = (TextView) itemView.findViewById(R.id.nome_paciente);
        dataEntrada= (TextView) itemView.findViewById(R.id.data_entrada);
        dataUltimoAtendimento = (TextView) itemView.findViewById(R.id.data_ultimo_atendimento);
        itemView.setOnLongClickListener(this);
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
        this.nomePaciente.setText(paciente.getNome());
        this.dataEntrada.setText(paciente.getDataEntrada());
        this.dataUltimoAtendimento.setText(paciente.getDataNascimento());
    }

    @Override
    public boolean onLongClick(View v) {
        Snackbar.make(v, paciente.getNome(), Snackbar.LENGTH_LONG).show();
        return false;
    }
}
