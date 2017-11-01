package tcc.marcelo.com.br.sadp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.view.adapter.PacientesAdapter;

/**
 * Created by Marcelo S. de Azevedo on 27/09/2017.
 */
public class ListaPacientesFragment extends MyFragment {

    private RecyclerView recyclerView;
    private HomeActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.listar_pacientes_fragment, container, false);
        mainActivity = (HomeActivity) getActivity();
        mainActivity.getSupportActionBar().setTitle("PACIENTES");
        mainActivity.preparaEditarPaciente(null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragment.getContext());
        recyclerView = (RecyclerView) fragment.findViewById(R.id.pacientes_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Paciente> result = realm.where(Paciente.class).findAll();
        Log.i("INFO", "Tamanho: " + result.size());
        List<Paciente> pacientes = realm.copyFromRealm(result);
        for(Paciente p : pacientes){
            Log.i("INFO", p.toString());
        }
        recyclerView.setAdapter(new PacientesAdapter(pacientes));

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
