package tcc.marcelo.com.br.sadp.view.holder;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Consulta;
import tcc.marcelo.com.br.sadp.util.StringUtil;

/**
 * Created by marcelo on 06/10/2017.
 */
public class AtendimentoHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    private boolean atendimentosColpased = true;
    private TextInputEditText dataConsulta = null;
    private TextInputEditText horaInicio = null;
    private TextInputEditText horaTermino = null;
    private TextInputEditText descricao = null;
    private CardView atendimentoCardView = null;
    private Consulta consulta;

    public AtendimentoHolder(View itemVIew){
        super(itemVIew);
        dataConsulta = (TextInputEditText) itemVIew.findViewById(R.id.atendimento_data_consulta);
        dataConsulta.setEnabled(false);
        dataConsulta.setFocusable(false);
        horaInicio = (TextInputEditText) itemVIew.findViewById(R.id.atendimento_hora_inicio);
        horaInicio.setEnabled(false);
        horaInicio.setFocusable(false);
        horaTermino = (TextInputEditText) itemVIew.findViewById(R.id.atendimento_hora_termino);
        horaTermino.setEnabled(false);
        horaTermino.setFocusable(false);
        descricao = (TextInputEditText) itemVIew.findViewById(R.id.atendimento_descricao);
        descricao.setEnabled(false);
        descricao.setFocusable(false);
        atendimentoCardView = (CardView) itemVIew.findViewById(R.id.atendimento_cardview);
        itemVIew.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        Snackbar.make(v, "TUDO DEPENDE DO QUANTO TU QUER COMER ALGUÉM!", Snackbar.LENGTH_LONG).show();
        return false;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
        this.dataConsulta.setText(consulta.getDataConsulta());
        this.horaInicio.setText(consulta.getHoraInicio());
        this.horaTermino.setText(consulta.getHoraTermino());
        this.descricao.setText(consulta.getDescricao());
    }
}
