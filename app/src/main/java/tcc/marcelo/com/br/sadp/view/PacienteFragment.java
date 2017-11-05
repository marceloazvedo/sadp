package tcc.marcelo.com.br.sadp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.dto.DefaultReponse;
import tcc.marcelo.com.br.sadp.dto.PacienteDTO;
import tcc.marcelo.com.br.sadp.dto.PacientesRespone;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.parser.PacienteParser;
import tcc.marcelo.com.br.sadp.service.IPsiquiatraService;
import tcc.marcelo.com.br.sadp.util.ActivityUtil;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.util.Mask;
import tcc.marcelo.com.br.sadp.util.SharedPreferencesUtil;
import tcc.marcelo.com.br.sadp.view.dialog.ErroConexaoDialog;

/**
 * Created by Marcelo S. de Azevedo on 28/09/2017.
 */
public class PacienteFragment extends MyFragment {

    private HomeActivity homeActivity;
    private Paciente paciente;
    private EditText txtNome;
    private EditText txtDataEntrada;
    private EditText txtDataNascimento;
    private EditText txtDescricao;
    private Button btnCadastrarAtualizarPaciente;
    private IPsiquiatraService psiquiatraService;
    private ProgressDialog mProgress;
    private SharedPreferencesUtil sharedPreferencesUtil;
    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.paciente_fragment, container, false);
        homeActivity = ((HomeActivity) getActivity());
        paciente = homeActivity.getPaciente();

        homeActivity.getSupportActionBar().setTitle("CADASTRAR PACIENTE");
        homeActivity.setDrawerState(false);

        txtNome = (EditText) fragment.findViewById(R.id.txt_nome_paciente);
        txtDataEntrada = (EditText) fragment.findViewById(R.id.txt_data_entrada);
        txtDataEntrada.addTextChangedListener(Mask.insert("##/##/####", txtDataEntrada));
        txtDataNascimento = (EditText) fragment.findViewById(R.id.txt_data_nascimento);
        txtDataNascimento.addTextChangedListener(Mask.insert("##/##/####", txtDataNascimento));
        txtDescricao = (EditText) fragment.findViewById(R.id.txt_descricao_paciente);
        btnCadastrarAtualizarPaciente = (Button) fragment.findViewById(R.id.btn_cadastrar_paciente);
        if (paciente != null) {
            txtNome.setText(paciente.getNome());
            txtDataEntrada.setText(paciente.getDataEntrada());
            txtDataNascimento.setText(paciente.getDataNascimento());
            txtDescricao.setText(paciente.getDescricao());
            btnCadastrarAtualizarPaciente.setText(R.string.editar);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sadp-service.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        psiquiatraService = retrofit.create(IPsiquiatraService.class);
        sharedPreferencesUtil = new SharedPreferencesUtil(homeActivity);

        btnCadastrarAtualizarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgress = ProgressDialog.show(homeActivity, "", "Salvando paciente");
                final PacienteDTO p = new PacienteDTO();
                p.setNome(txtNome.getText().toString());
                p.setDescricao(txtDescricao.getText().toString());
                p.setDataEntrada(txtDataEntrada.getText().toString());
                p.setDataNascimento(txtDataNascimento.getText().toString());

                Call<DefaultReponse> call = psiquiatraService.cadastrarPaciente(sharedPreferencesUtil.getTokenApp(), p);
                call.enqueue(new Callback<DefaultReponse>() {
                    @Override
                    public void onResponse(Call<DefaultReponse> call, Response<DefaultReponse> response) {
                        if(response.code() == 200){
                            mProgress.dismiss();
                            DefaultReponse body = response.body();
                            Log.i("INFO", "resposta: " + body.getCodigo() + " - " + body.getMensagem());
                            if(body.getCodigo().equals("000")){
                                FragmentManagerUtil.trocarFragment(homeActivity, new ListaPacientesFragment());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DefaultReponse> call, Throwable t) {
                        mProgress.dismiss();
                        ErroConexaoDialog erroConexaoDialog = new ErroConexaoDialog();
                        erroConexaoDialog.show(getFragmentManager(), "missiles");
                    }
                });
            }
        });
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homeActivity.setDrawerState(true);
    }

    @Override
    public String getFragmentTag() {
        return "PacienteFragment";
    }

}
