package tcc.marcelo.com.br.sadp.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.dto.DefaultReponse;
import tcc.marcelo.com.br.sadp.dto.SaveConsultaDTO;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.model.Sintoma;
import tcc.marcelo.com.br.sadp.parser.CaracteristicaParser;
import tcc.marcelo.com.br.sadp.service.IPsiquiatraService;
import tcc.marcelo.com.br.sadp.singleton.GerenciadorSelecaoSintomas;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.util.SharedPreferencesUtil;
import tcc.marcelo.com.br.sadp.view.ConsultaFragment;
import tcc.marcelo.com.br.sadp.view.HomeActivity;
import tcc.marcelo.com.br.sadp.view.VisualizarPacienteFragment;

/**
 * Created by marcelo on 11/10/17.
 */

public class FinalizarConsultaDialog extends DialogFragment {

    private HomeActivity homeActivity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.deseja_finalizar_consulta)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        homeActivity = (HomeActivity) getActivity();

                        final String idPaciente = getArguments().getString("paciente");
                        String descricao = getArguments().getString("descricao");
                        String horaInicio = getArguments().getString("horaInicio");
                        String horaTermino = getArguments().getString("horaTermino");

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("https://sadp-service.herokuapp.com")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        IPsiquiatraService psiquiatraService = retrofit.create(IPsiquiatraService.class);
                        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(getActivity());

                        SaveConsultaDTO consulta = new SaveConsultaDTO();
                        consulta.setDescricao(descricao);
                        consulta.setHoraInicio(horaInicio);
                        consulta.setHoraTermino(horaTermino);
                        consulta.setCaracteristicasEncontradas(CaracteristicaParser.getListaCaractersiticasID(GerenciadorSelecaoSintomas.getInstance().getSintomas()));

                        Call<DefaultReponse> call = psiquiatraService.cadastrarConsulta(sharedPreferencesUtil.getTokenApp(), idPaciente, consulta);
                        call.enqueue(new Callback<DefaultReponse>() {
                            @Override
                            public void onResponse(Call<DefaultReponse> call, Response<DefaultReponse> response) {
                                if(response.code() == 200){
                                    if(response.body().getCodigo().equals("000")){
                                        VisualizarPacienteFragment visualizarPacienteFragment = new VisualizarPacienteFragment();
                                        Bundle bundle = new Bundle();
                                        bundle.putString("paciente", idPaciente);
                                        visualizarPacienteFragment.setArguments(bundle);
                                        FragmentManagerUtil.trocarFragment(homeActivity, visualizarPacienteFragment);
                                    } else {
                                        Log.e("ERRO", "Algo errado não está certo: " + response.body().toString());
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultReponse> call, Throwable t) {

                            }
                        });

                        // Resolver bug do voltar volar

                    }
                })
                .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
