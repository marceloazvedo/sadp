package tcc.marcelo.com.br.sadp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.dto.ConsultaDTO;
import tcc.marcelo.com.br.sadp.dto.ConsultasResponse;
import tcc.marcelo.com.br.sadp.model.Consulta;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.parser.ConsultaParser;
import tcc.marcelo.com.br.sadp.service.IPsiquiatraService;
import tcc.marcelo.com.br.sadp.util.SharedPreferencesUtil;
import tcc.marcelo.com.br.sadp.util.StringUtil;
import tcc.marcelo.com.br.sadp.view.adapter.AtendimentoAdapter;
import tcc.marcelo.com.br.sadp.view.dialog.ErroConexaoDialog;
import tcc.marcelo.com.br.sadp.view.dialog.IniciarConsultaDialog;

/**
 * Created by GATI on 06/10/2017.
 */

public class VisualizarPacienteFragment extends MyFragment {

    private HomeActivity homeActivity;
    private TextInputEditText nomePaciente;
    private TextInputEditText dataUltimoAtendimento;
    private TextInputEditText dataCadastro;
    private Paciente paciente;
    private RecyclerView atendimentoRecyclerView;
    private Button btnNovaConsulta;
    private IPsiquiatraService psiquiatraService;
    private ProgressDialog mProgress;
    private SharedPreferencesUtil sharedPreferencesUtil;
    private List<Consulta> consultas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.visualizar_paciente_fragment, container, false);
        homeActivity = (HomeActivity) getActivity();
        this.paciente = homeActivity.getPaciente();

        nomePaciente = (TextInputEditText) fragment.findViewById(R.id.edt_nome_paciente);
        dataUltimoAtendimento = (TextInputEditText) fragment.findViewById(R.id.edt_data_ultimo_atendimento);
        dataCadastro= (TextInputEditText) fragment.findViewById(R.id.edt_data_entrada);

        homeActivity.getSupportActionBar().setTitle("VISUALIZAR PACIENTE");
        homeActivity.setDrawerState(false);
        homeActivity.supportInvalidateOptionsMenu();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragment.getContext());
        atendimentoRecyclerView = (RecyclerView) fragment.findViewById(R.id.atendimento_rv);
        atendimentoRecyclerView.setLayoutManager(linearLayoutManager);

        buscarConsultas();

        btnNovaConsulta = (Button) fragment.findViewById(R.id.btn_nova_consulta);
        btnNovaConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarConsultaDialog iniciarConsultaDialog = new IniciarConsultaDialog();
                iniciarConsultaDialog.show(getFragmentManager(), "missiles");
            }
        });

        preencherInformacoes();

        return fragment;
    }

    private void preencherInformacoes(){
        nomePaciente.setText(paciente.getNome());
        dataUltimoAtendimento.setText(paciente.getDataNascimento());
        dataCadastro.setText(paciente.getDataEntrada());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homeActivity.setDrawerState(true);
    }

    @Override
    public String getFragmentTag() {
        return "VisualizarPacienteFragment";
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void buscarConsultas(){
        mProgress = ProgressDialog.show(homeActivity, "", getResources().getString(R.string.sincronizando));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sadp-service.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        psiquiatraService = retrofit.create(IPsiquiatraService.class);
        sharedPreferencesUtil = new SharedPreferencesUtil(homeActivity);

        Call<ConsultasResponse> call = psiquiatraService.getConsultas(sharedPreferencesUtil.getTokenApp(), paciente.getId());
        call.enqueue(new Callback<ConsultasResponse>() {
            @Override
            public void onResponse(Call<ConsultasResponse> call, Response<ConsultasResponse> response) {
                ConsultasResponse body = response.body();

                consultas = ConsultaParser.parserList(body.getConsultas());
                atendimentoRecyclerView.setAdapter(new AtendimentoAdapter(consultas));

                mProgress.dismiss();
            }

            @Override
            public void onFailure(Call<ConsultasResponse> call, Throwable t) {
                mProgress.dismiss();
                ErroConexaoDialog erroConexaoDialog = new ErroConexaoDialog();
                erroConexaoDialog.show(getFragmentManager(), "missiles");
            }
        });
    }

}
