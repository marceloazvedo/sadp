package tcc.marcelo.com.br.sadp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;

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
        DecimalFormat format = new DecimalFormat("#.##");
        String percentual = format.format(esquizofrenia.getPercentual()).replace(".", "").concat(" %");
        this.lbPercentual.setText(percentual);
        this.progressBar.setProgress(esquizofrenia.getPercentual().intValue());
    }
}
