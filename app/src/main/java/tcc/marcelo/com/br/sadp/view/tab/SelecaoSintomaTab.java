package tcc.marcelo.com.br.sadp.view.tab;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.dto.CaracteristicasResponse;
import tcc.marcelo.com.br.sadp.dto.ConsultasResponse;
import tcc.marcelo.com.br.sadp.enums.TipoSintomaAdapter;
import tcc.marcelo.com.br.sadp.model.Sintoma;
import tcc.marcelo.com.br.sadp.parser.ConsultaParser;
import tcc.marcelo.com.br.sadp.parser.SintomaParser;
import tcc.marcelo.com.br.sadp.service.IPsiquiatraService;
import tcc.marcelo.com.br.sadp.singleton.GerenciadorSelecaoSintomas;
import tcc.marcelo.com.br.sadp.util.SharedPreferencesUtil;
import tcc.marcelo.com.br.sadp.view.adapter.AtendimentoAdapter;
import tcc.marcelo.com.br.sadp.view.adapter.SintomaAdapter;
import tcc.marcelo.com.br.sadp.view.dialog.ErroConexaoDialog;

import static android.R.attr.fragment;

/**
 * Created by marcelo on 10/10/17.
 */
public class SelecaoSintomaTab extends Fragment {

    private IPsiquiatraService psiquiatraService;
    private ProgressDialog mProgress;
    private SharedPreferencesUtil sharedPreferencesUtil;
    private List<Sintoma> sintomas;
    private List<Sintoma> todosSintomas;
    private View view;
    private RecyclerView recyclerViewSintomas;
    private SintomaAdapter adapterTodosSintomas;
    private ImageButton imgButtonBuscar;
    private EditText txtBusca;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_selecao_sintomas, container, false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerViewSintomas = (RecyclerView) view.findViewById(R.id.rv_lista_sintomas);
        recyclerViewSintomas.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSintomas.setLayoutManager(layoutManager);

        adapterTodosSintomas = new SintomaAdapter(sintomas, TipoSintomaAdapter.ADD);
        recyclerViewSintomas.setAdapter(adapterTodosSintomas);

        buscarSintomas();

        txtBusca = (EditText) view.findViewById(R.id.txt_buscar_sintoma);
        imgButtonBuscar = (ImageButton) view.findViewById(R.id.img_btn_buscar);
        imgButtonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String busca = txtBusca.getText().toString();
                if(busca == null || busca.equals("")){
                    adapterTodosSintomas.setSintomas(todosSintomas);
                } else {
                    List<Sintoma> resultado = new ArrayList<Sintoma>();
                    for(Sintoma sintoma : todosSintomas){
                        if(sintoma.getDescrica().toLowerCase().contains(busca.toLowerCase())){
                            resultado.add(sintoma);
                        }
                    }
                    adapterTodosSintomas.setSintomas(resultado);
                }
                adapterTodosSintomas.notifyDataSetChanged();
            }
        });

        return view;
    }

    public void buscarSintomas(){
        mProgress = ProgressDialog.show(view.getContext(), "", getResources().getString(R.string.sincronizando));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sadp-service.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        psiquiatraService = retrofit.create(IPsiquiatraService.class);
        sharedPreferencesUtil = new SharedPreferencesUtil(view.getContext());

        Call<CaracteristicasResponse> call = psiquiatraService.getCaracteristicas(sharedPreferencesUtil.getTokenApp());
        call.enqueue(new Callback<CaracteristicasResponse>() {
            @Override
            public void onResponse(Call<CaracteristicasResponse> call, Response<CaracteristicasResponse> response) {
                CaracteristicasResponse body = response.body();

                sintomas = SintomaParser.parserList(body.getCaracteristicas());
                todosSintomas = SintomaParser.parserList(body.getCaracteristicas());
                adapterTodosSintomas.setSintomas(sintomas);
                adapterTodosSintomas.notifyDataSetChanged();

                mProgress.dismiss();
            }

            @Override
            public void onFailure(Call<CaracteristicasResponse> call, Throwable t) {
                mProgress.dismiss();
                ErroConexaoDialog erroConexaoDialog = new ErroConexaoDialog();
                Activity activity = (Activity) view.getContext();
                erroConexaoDialog.show(activity.getFragmentManager(), "missiles");
            }
        });
    }

    public List<Sintoma> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<Sintoma> sintomas) {
        this.sintomas = sintomas;
    }

    public SintomaAdapter getAdapterTodosSintomas() {
        return adapterTodosSintomas;
    }

    public void setAdapterTodosSintomas(SintomaAdapter adapterTodosSintomas) {
        this.adapterTodosSintomas = adapterTodosSintomas;
    }

}
