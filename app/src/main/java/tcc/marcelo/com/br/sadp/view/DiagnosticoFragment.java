package tcc.marcelo.com.br.sadp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.dto.DiagnosticoResponse;
import tcc.marcelo.com.br.sadp.dto.EsquizofreniaDiagnosticoDTO;
import tcc.marcelo.com.br.sadp.service.IPsiquiatraService;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.util.SharedPreferencesUtil;
import tcc.marcelo.com.br.sadp.view.adapter.EsquizofreniaAdapter;
import tcc.marcelo.com.br.sadp.view.dialog.ErroConexaoDialog;

/**
 * Created by marcelo on 05/11/17.
 */
public class DiagnosticoFragment extends MyFragment {

    private ProgressDialog mProgress;
    private HomeActivity homeActivity;
    private IPsiquiatraService psiquiatraService;
    private RecyclerView recyclerView;
    private String idPaciente;
    private List<EsquizofreniaDiagnosticoDTO> esquizofrenias;
    private String nomePaciente;
    private TextView lbNomePaciente;
    private Button btnOk;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.diagnostico, container, false);
        homeActivity = ((HomeActivity) getActivity());
        idPaciente = getArguments().getString("paciente");
        lbNomePaciente = (TextView) fragment.findViewById(R.id.lb_nome_paciente_diagnostico);

        homeActivity =(HomeActivity) getActivity();
        homeActivity.setDrawerState(false);
        homeActivity.supportInvalidateOptionsMenu();
        homeActivity.getSupportActionBar().setTitle("DIAGNÓSTICO");


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragment.getContext());
        recyclerView = (RecyclerView) fragment.findViewById(R.id.rc_lista_diagnostico);
        recyclerView.setLayoutManager(linearLayoutManager);

        buscarDiagnostico();

        btnOk = (Button) fragment.findViewById(R.id.btn_ok_diagnostico);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManagerUtil.popBackStack(homeActivity);
            }
        });

        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return "DiagnosticoFragment";
    }

    public void buscarDiagnostico(){
        mProgress = ProgressDialog.show(homeActivity, "", getResources().getString(R.string.sincronizando));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sadp-service.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        psiquiatraService = retrofit.create(IPsiquiatraService.class);

        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(homeActivity);

        Call<DiagnosticoResponse> call = psiquiatraService.diagnotico(sharedPreferencesUtil.getTokenApp(), idPaciente);
        call.enqueue(new Callback<DiagnosticoResponse>() {
            @Override
            public void onResponse(Call<DiagnosticoResponse> call, Response<DiagnosticoResponse> response) {
                DiagnosticoResponse diagnosticoResponse = response.body();

                esquizofrenias = diagnosticoResponse.getDiagnostico();
                Log.i("INFO", "Quantidade de esquizofrenias: " + diagnosticoResponse.getDiagnostico().size());
                for(EsquizofreniaDiagnosticoDTO e : esquizofrenias){
                    Log.i("INFO", "Esquizofrenia: " + e.getEsquizofrenia() + " percentual: " + e.getPercentual());
                }
                nomePaciente = diagnosticoResponse.getPaciente();
                lbNomePaciente.setText(nomePaciente);

                recyclerView.setAdapter(new EsquizofreniaAdapter(esquizofrenias));

                mProgress.dismiss();
            }

            @Override
            public void onFailure(Call<DiagnosticoResponse> call, Throwable t) {
                mProgress.dismiss();
                ErroConexaoDialog erroConexaoDialog = new ErroConexaoDialog();
                erroConexaoDialog.show(getFragmentManager(), "missiles");
            }
        });

    }
}
