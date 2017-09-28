package tcc.marcelo.com.br.sadp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.view.adapter.PacientesAdapter;

/**
 * Created by Marcelo S. de Azevedo on 27/09/2017.
 */
public class ListaPacientesFragment extends MyFragment {

    private RecyclerView recyclerView = null;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.listar_pacientes_fragment, container, false);
        ((HomeActivity) getActivity()).getSupportActionBar().setTitle("PACIENTES");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragment.getContext());
        recyclerView = (RecyclerView) fragment.findViewById(R.id.pacientes_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
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

        // Caso seja necessário adicionar um divisor entre as linhas do recyclerview
        //recyclerView.addItemDecoration(
        //        new DividerItemDecoration(fragment.getContext(), DividerItemDecoration.VERTICAL));

        FloatingActionButton floatingButtonAddPaciente = (FloatingActionButton) fragment.findViewById(R.id.float_add_paciente);
        floatingButtonAddPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManagerUtil.trocarFragment(getActivity(), new PacienteFragment());
            }
        });
        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return "ListaPacientesFragment";
    }
}
