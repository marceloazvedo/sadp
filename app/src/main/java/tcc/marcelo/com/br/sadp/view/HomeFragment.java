package tcc.marcelo.com.br.sadp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.realm.Realm;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.dto.PacientesRespone;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.parser.PacienteParser;
import tcc.marcelo.com.br.sadp.service.IPsiquiatraService;
import tcc.marcelo.com.br.sadp.util.SharedPreferencesUtil;

/**
 * Created by marcelo on 26/09/2017.
 */
public class HomeFragment extends MyFragment {

    private IPsiquiatraService psiquiatraService;
    private SharedPreferencesUtil sharedPreferencesUtil;
    private ProgressDialog mProgress;
    private HomeActivity mainActivity;
    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.home_fragment, container, false);
        ((HomeActivity) getActivity()).getSupportActionBar().setTitle("SADP");
        mainActivity =(HomeActivity) getActivity();

        sharedPreferencesUtil = new SharedPreferencesUtil(mainActivity);
        preencherBase();

        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return "HomeFragment";
    }

    public void preencherBase(){
        mProgress = ProgressDialog.show(mainActivity, "", getResources().getString(R.string.sincronizando));

        realm = Realm.getDefaultInstance();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sadp-service.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        psiquiatraService = retrofit.create(IPsiquiatraService.class);

        retrofit2.Call<PacientesRespone> call = psiquiatraService.getPacientes(sharedPreferencesUtil.getTokenApp());
        call.enqueue(new Callback<PacientesRespone>() {
            @Override
            public void onResponse(retrofit2.Call<PacientesRespone> call, Response<PacientesRespone> response) {
                realm.beginTransaction();
                realm.deleteAll();

                PacientesRespone pacientesRespone = response.body();
                List<Paciente> pacientes = PacienteParser.parserList(pacientesRespone.getPacientes());
                for(Paciente paciente : pacientes){
                    realm.copyToRealm(paciente);
                }

                realm.commitTransaction();
                mProgress.dismiss();
            }

            @Override
            public void onFailure(retrofit2.Call<PacientesRespone> call, Throwable t) {
                mProgress.dismiss();
                Snackbar.make(getView(), R.string.erro_conexao, Snackbar.LENGTH_INDEFINITE).show();
            }
        });


    }

}
