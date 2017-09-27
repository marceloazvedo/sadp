package tcc.marcelo.com.br.sadp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.view.adapter.PacientesAdapter;

/**
 * Created by Marcelo S. de Azevedo on 27/09/2017.
 */
public class ListaPacientesFragment extends MyFragment {

    private RecyclerView recyclerView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.listar_pacientes_fragment, container, false);
        recyclerView = (RecyclerView) fragment.findViewById(R.id.pacientes_recycler_view);
        List<Paciente> pacientes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Paciente paciente = new Paciente();
            paciente.setDataEntrada("12/04/201" + i);
            paciente.setDataNascimento("05/05/199" + i);
            paciente.setDescricao("testando essa buceta");
            paciente.setNome("Marcelo " + i);
            pacientes.add(paciente);
        }
        recyclerView.setAdapter(new PacientesAdapter(pacientes));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(fragment.getContext(), DividerItemDecoration.VERTICAL));
        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return "ListaPacientesFragment";
    }
}
