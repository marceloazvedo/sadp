package tcc.marcelo.com.br.sadp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.enums.TipoSintomaAdapter;
import tcc.marcelo.com.br.sadp.model.Sintoma;
import tcc.marcelo.com.br.sadp.singleton.GerenciadorSelecaoSintomas;
import tcc.marcelo.com.br.sadp.view.adapter.SintomaAdapter;

/**
 * Created by marcelo on 10/10/17.
 */
public class SintomaHolder extends RecyclerView.ViewHolder {

    private ImageButton btnDeletar;
    private ImageButton btnAdicionar;
    private TextView txtDescricaoSintoma;
    private Sintoma sintoma;
    private SintomaAdapter adapter;

    public SintomaHolder(View itemView, SintomaAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        btnDeletar = (ImageButton) itemView.findViewById(R.id.btn_remover_sintoma);

        btnAdicionar = (ImageButton) itemView.findViewById(R.id.btn_add_sintoma);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("INFO", "Sintoma: " + sintoma.getDescrica());
                SintomaHolder.this.adapter.remove(getAdapterPosition());
                GerenciadorSelecaoSintomas.getInstance().add(sintoma);
            }
        });
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SintomaHolder.this.adapter.remove(getAdapterPosition());
            }
        });

        if(adapter.getTipoAdapter() == TipoSintomaAdapter.ADD) {
            btnDeletar.setVisibility(View.GONE);
        } else {
            btnAdicionar.setVisibility(View.GONE);
        }

        txtDescricaoSintoma = (TextView) itemView.findViewById(R.id.txt_descricao_sintoma);
    }

    public Sintoma getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintoma sintoma) {
        txtDescricaoSintoma.setText(sintoma.getDescrica());
        this.sintoma = sintoma;
    }

    public SintomaAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(SintomaAdapter adapter) {
        this.adapter = adapter;
    }

}
