package tcc.marcelo.com.br.sadp.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.dto.DefaultReponse;
import tcc.marcelo.com.br.sadp.dto.PacientesRespone;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.parser.PacienteParser;
import tcc.marcelo.com.br.sadp.service.IPsiquiatraService;
import tcc.marcelo.com.br.sadp.util.ActivityUtil;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.util.SharedPreferencesUtil;
import tcc.marcelo.com.br.sadp.view.HomeActivity;
import tcc.marcelo.com.br.sadp.view.ListaPacientesFragment;
import tcc.marcelo.com.br.sadp.view.VisualizarPacienteFragment;

/**
 * Created by GATI on 06/10/2017.
 */

public class DeletarPacienteDialog extends DialogFragment {

    private HomeActivity homeActivity;
    private ProgressDialog mProgress;
    private IPsiquiatraService psiquiatraService;
    private SharedPreferencesUtil sharedPreferencesUtil;
    private Retrofit retrofit;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String mensagem = String.format(getResources().getString(R.string.deseja_deletar_paciente), ((HomeActivity) getActivity()).getPaciente().getNome());
        builder.setMessage(mensagem)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        homeActivity = (HomeActivity) getActivity();
                        mProgress = ProgressDialog.show(homeActivity, "", "Removendo paciente, aguarde...");

                        String idPaciente = getArguments().getString("paciente");

                        retrofit = new Retrofit.Builder()
                                .baseUrl("https://sadp-service.herokuapp.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        psiquiatraService = retrofit.create(IPsiquiatraService.class);
                        sharedPreferencesUtil = new SharedPreferencesUtil(getActivity());

                        Call<DefaultReponse> call = psiquiatraService.removerPaciente(sharedPreferencesUtil.getTokenApp(), idPaciente);
                        call.enqueue(new Callback<DefaultReponse>() {
                            @Override
                            public void onResponse(Call<DefaultReponse> call, Response<DefaultReponse> response) {
                                if(response.code() == 200){
                                    if(response.body().getCodigo().equals("000")){
                                        mProgress.dismiss();
                                        FragmentManagerUtil.trocarFragment(homeActivity, new ListaPacientesFragment());
                                    } else {
                                        Log.e("ERRO", "Algo errado não está certo: " + response.body().toString());
                                        mProgress.dismiss();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultReponse> call, Throwable t) {
                                mProgress.dismiss();
                                ErroConexaoDialog erroConexaoDialog = new ErroConexaoDialog();
                                DeletarPacienteDialog.this.dismiss();
                                erroConexaoDialog.show(getFragmentManager(), "missiles");
                            }
                        });
                    }
                })
                .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        return builder.create();
    }
}
