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
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Sintoma;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.view.HomeActivity;
import tcc.marcelo.com.br.sadp.view.VisualizarPacienteFragment;
import tcc.marcelo.com.br.sadp.view.adapter.SintomaAdapter;
import tcc.marcelo.com.br.sadp.view.dialog.FinalizarConsultaDialog;

/**
 * Created by marcelo on 10/10/17.
 */
public class FinalizarConsultaTab extends Fragment {

    private Button btnFinalizarConsulta;
    private HomeActivity homeActivity;
    private List<Sintoma> sintomasSelecionados;
    private RecyclerView recyclerViewSintomas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_finalizar_consulta, container, false);
        homeActivity = (HomeActivity) getActivity();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerViewSintomas = (RecyclerView) view.findViewById(R.id.rv_sintomas_selecionados);
        recyclerViewSintomas.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSintomas.setLayoutManager(linearLayoutManager);

        recyclerViewSintomas.setAdapter(new SintomaAdapter(sintomasSelecionados));


        btnFinalizarConsulta = (Button) view.findViewById(R.id.btn_finalizar_consulta);
        btnFinalizarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalizarConsultaDialog confirmarDialog = new FinalizarConsultaDialog();
                confirmarDialog.show(homeActivity.getFragmentManager(), "missiles");
            }
        });
        return view;
    }

    public List<Sintoma> getSintomasSelecionados() {
        return sintomasSelecionados;
    }

    public void setSintomasSelecionados(List<Sintoma> sintomasSelecionados) {
        this.sintomasSelecionados = sintomasSelecionados;
    }
}
