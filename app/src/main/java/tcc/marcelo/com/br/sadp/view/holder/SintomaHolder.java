package tcc.marcelo.com.br.sadp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import tcc.marcelo.com.br.sadp.R;

/**
 * Created by marcelo on 10/10/17.
 */
public class SintomaHolder extends RecyclerView.ViewHolder {

    private ImageButton btnDeletar;
    private ImageButton btnVisualizar;
    private ImageButton btnAdicionar;

    public SintomaHolder(View itemView) {
        super(itemView);

        btnDeletar = (ImageButton) itemView.findViewById(R.id.btn_remover_sintoma);
        btnDeletar.setVisibility(View.GONE);
        btnVisualizar = (ImageButton) itemView.findViewById(R.id.btn_visualizar_sintoma);
        btnAdicionar = (ImageButton) itemView.findViewById(R.id.btn_add_sintoma);
    }
}
