package tcc.marcelo.com.br.sadp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Sintoma;

/**
 * Created by marcelo on 10/10/17.
 */
public class SintomaHolder extends RecyclerView.ViewHolder {

    private ImageButton btnDeletar;
    private ImageButton btnVisualizar;
    private ImageButton btnAdicionar;
    private TextView txtDescricaoSintoma;
    private Sintoma sintoma;

    public SintomaHolder(View itemView) {
        super(itemView);
        btnDeletar = (ImageButton) itemView.findViewById(R.id.btn_remover_sintoma);
        btnDeletar.setVisibility(View.GONE);
        btnVisualizar = (ImageButton) itemView.findViewById(R.id.btn_visualizar_sintoma);
        btnAdicionar = (ImageButton) itemView.findViewById(R.id.btn_add_sintoma);

        txtDescricaoSintoma = (TextView) itemView.findViewById(R.id.txt_descricao_sintoma);
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        txtDescricaoSintoma.setText(sintoma.getDescrica());
        this.sintoma = sintoma;
    }
}
