package tcc.marcelo.com.br.sadp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.dto.PacientesRespone;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.parser.PacienteParser;
import tcc.marcelo.com.br.sadp.service.IPsiquiatraService;
import tcc.marcelo.com.br.sadp.util.ActivityUtil;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.util.SharedPreferencesUtil;
import tcc.marcelo.com.br.sadp.view.adapter.PacientesAdapter;

/**
 * Created by Marcelo S. de Azevedo on 27/09/2017.
 */
public class ListaPacientesFragment extends MyFragment {

    private RecyclerView recyclerView;
    private HomeActivity homeActivity;
    private Realm realm;
    private ProgressDialog mProgress;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.listar_pacientes_fragment, container, false);
        homeActivity = (HomeActivity) getActivity();
        homeActivity.getSupportActionBar().setTitle("PACIENTES");
        homeActivity.preparaEditarPaciente(null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragment.getContext());
        recyclerView = (RecyclerView) fragment.findViewById(R.id.pacientes_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Paciente> result = realm.where(Paciente.class).findAll();
        List<Paciente> pacientes = realm.copyFromRealm(result);
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
    public void onResume() {
        mProgress = ProgressDialog.show(homeActivity, "", "Atualizando lista de pacientes...");

        realm = Realm.getDefaultInstance();
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(homeActivity);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sadp-service.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IPsiquiatraService psiquiatraService = retrofit.create(IPsiquiatraService.class);

        Call<PacientesRespone> call = psiquiatraService.getPacientes(sharedPreferencesUtil.getTokenApp());
        call.enqueue(new Callback<PacientesRespone>() {
            @Override
            public void onResponse(retrofit2.Call<PacientesRespone> call, Response<PacientesRespone> response) {
                realm.beginTransaction();
                realm.deleteAll();

                if(response.code() == 401){
                    ActivityUtil.logout(homeActivity);
                } else {
                    PacientesRespone pacientesRespone = response.body();
                    List<Paciente> pacientes = PacienteParser.parserList(pacientesRespone.getPacientes());
                    recyclerView.setAdapter(new PacientesAdapter(pacientes));
                    for(Paciente paciente : pacientes){
                        realm.copyToRealm(paciente);
                    }
                    mProgress.dismiss();
                }
                realm.commitTransaction();
            }

            @Override
            public void onFailure(retrofit2.Call<PacientesRespone> call, Throwable t) {
                mProgress.dismiss();
                Snackbar.make(getView(), R.string.erro_conexao, Snackbar.LENGTH_INDEFINITE).show();
            }
        });

        super.onResume();
    }

    @Override
    public String getFragmentTag() {
        return "ListaPacientesFragment";
    }
}
