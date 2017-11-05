package tcc.marcelo.com.br.sadp.view.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.dto.CaracteristicasResponse;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.model.Sintoma;
import tcc.marcelo.com.br.sadp.parser.SintomaParser;
import tcc.marcelo.com.br.sadp.service.IPsiquiatraService;
import tcc.marcelo.com.br.sadp.util.SharedPreferencesUtil;
import tcc.marcelo.com.br.sadp.view.dialog.ErroConexaoDialog;
import tcc.marcelo.com.br.sadp.view.tab.FinalizarConsultaTab;
import tcc.marcelo.com.br.sadp.view.tab.SelecaoSintomaTab;

/**
 * Created by marcelo on 10/10/17.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;
    private SelecaoSintomaTab sintomaTab;
    private FinalizarConsultaTab finalizarConsultaTab;
    private SintomaAdapter adapterTodosSintomas;
    private SintomaAdapter adapterSintomasSelecionados;
    private String idPaciente;

    public MyPagerAdapter(FragmentManager fm, int numOfTabs, String idPaciente) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.sintomaTab = new SelecaoSintomaTab();
        Bundle bundle = new Bundle();
        bundle.putString("paciente", idPaciente);
        this.finalizarConsultaTab = new FinalizarConsultaTab();
        this.finalizarConsultaTab.setArguments(bundle);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return sintomaTab;
            }
            case 1:
                return finalizarConsultaTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
