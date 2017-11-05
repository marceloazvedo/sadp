package tcc.marcelo.com.br.sadp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.dto.EsquizofreniaDiagnosticoDTO;

/**
 * Created by marcelo on 05/11/17.
 */

public class EsquizofreniaDiagnosticoHolder extends RecyclerView.ViewHolder {

    private TextView lbNomeEsquizofrenia;
    private TextView lbPercentual;
    private ProgressBar progressBar;

    private EsquizofreniaDiagnosticoDTO esquizofrenia;

    public EsquizofreniaDiagnosticoHolder(View itemView) {
        super(itemView);
        lbNomeEsquizofrenia = (TextView) itemView.findViewById(R.id.lb_nome_esquizofrenia);
        lbPercentual = (TextView) itemView.findViewById(R.id.lb_percentual_diagnostico);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
    }

    public EsquizofreniaDiagnosticoDTO getEsquizofrenia() {
        return esquizofrenia;
    }

    public void setEsquizofrenia(EsquizofreniaDiagnosticoDTO esquizofrenia) {
        this.esquizofrenia = esquizofrenia;
        this.lbNomeEsquizofrenia.setText(esquizofrenia.getEsquizofrenia());
        this.lbPercentual.setText(esquizofrenia.getPercentual().toString());
        this.progressBar.setProgress(esquizofrenia.getPercentual().intValue());
    }
}
