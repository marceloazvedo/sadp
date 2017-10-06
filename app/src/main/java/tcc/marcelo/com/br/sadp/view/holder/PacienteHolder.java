package tcc.marcelo.com.br.sadp.view.holder;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.view.HomeActivity;
import tcc.marcelo.com.br.sadp.view.adapter.PacientesAdapter;

/**
 * Created by Marcelo S. de Azevedo on 27/09/2017.
 */
public class PacienteHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    private Paciente paciente;
    private TextView nomePaciente;
    private TextView dataEntrada;
    private TextView dataUltimoAtendimento;
    private CardView cardView;
    private PacientesAdapter adapter;

    public PacienteHolder(View itemView){
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.card_view);
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

        Activity activity = (Activity) itemView.getContext();
        if(activity instanceof HomeActivity) {
            HomeActivity homeActivity = (HomeActivity) activity;
            homeActivity.preparaEditarPaciente(this.paciente);
            homeActivity.supportInvalidateOptionsMenu();
        }

        adapter.setSelecionado(getAdapterPosition());
        adapter.notifyDataSetChanged();

        return false;
    }

    public void setSelecionado(boolean selecionado){
        if(selecionado) {
            cardView.setCardBackgroundColor(cardView.getResources().getColor(R.color.selecionado));
        } else {
            cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    public View getItemView(){
        return itemView;
    }

    public void setAdapter(PacientesAdapter adapter) {
        this.adapter = adapter;
    }
}
