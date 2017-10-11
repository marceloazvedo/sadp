package tcc.marcelo.com.br.sadp.view.tab;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Sintoma;
import tcc.marcelo.com.br.sadp.view.adapter.SintomaAdapter;

import static android.R.attr.fragment;

/**
 * Created by marcelo on 10/10/17.
 */

public class SelecaoSintomaTab extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_selecao_sintomas, container, false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
        RecyclerView recyclerViewSintomas = (RecyclerView) view.findViewById(R.id.rv_lista_sintomas);
        recyclerViewSintomas.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSintomas.setLayoutManager(layoutManager);
        List<Sintoma> sintomas = new ArrayList<>();
        for(int i = 0; i< 30; i++){
            Sintoma sintoma = new Sintoma();
            sintoma.setDescrica("qualquer");
            sintoma.setId(new Long(i));
            sintomas.add(sintoma);
        }
        recyclerViewSintomas.setAdapter(new SintomaAdapter(sintomas));

        return view;
    }
}
