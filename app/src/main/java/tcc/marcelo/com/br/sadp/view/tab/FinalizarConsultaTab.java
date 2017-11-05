package tcc.marcelo.com.br.sadp.view.tab;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.enums.TipoSintomaAdapter;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.model.Sintoma;
import tcc.marcelo.com.br.sadp.singleton.GerenciadorSelecaoSintomas;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.util.StringUtil;
import tcc.marcelo.com.br.sadp.view.HomeActivity;
import tcc.marcelo.com.br.sadp.view.VisualizarPacienteFragment;
import tcc.marcelo.com.br.sadp.view.adapter.SintomaAdapter;
import tcc.marcelo.com.br.sadp.view.dialog.FinalizarConsultaDialog;

/**
 * Created by marcelo on 10/10/17.
 */
public class FinalizarConsultaTab extends Fragment {

    private Button btnFinalizarConsulta;
    private ImageButton btnSincronizar;
    private HomeActivity homeActivity;
    private List<Sintoma> sintomasSelecionados;
    private RecyclerView recyclerViewSintomas;
    private SintomaAdapter sintomaSelecionadosAdapter;
    private EditText txtDescricao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, final Bundle savedInstanceState) {
        final String horaInicio = StringUtil.getHoraEMinuto(Calendar.getInstance().getTime());
        Log.i("INFO", "Hora inicio: " + horaInicio);
        View view = inflater.inflate(R.layout.tab_finalizar_consulta, container, false);
        homeActivity = (HomeActivity) getActivity();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerViewSintomas = (RecyclerView) view.findViewById(R.id.rv_sintomas_selecionados);
        recyclerViewSintomas.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSintomas.setLayoutManager(layoutManager);

        sintomaSelecionadosAdapter = new SintomaAdapter(sintomasSelecionados, TipoSintomaAdapter.DELETAR);
        recyclerViewSintomas.setAdapter(sintomaSelecionadosAdapter);

        txtDescricao = (EditText) view.findViewById(R.id.txt_descricao_consulta);

        btnSincronizar = (ImageButton) view.findViewById(R.id.img_btn_sincronizar);
        btnSincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog mProgress = new ProgressDialog(view.getContext());
                mProgress.show();
                sintomasSelecionados = GerenciadorSelecaoSintomas.getInstance().getSintomas();
                recyclerViewSintomas.setAdapter(new SintomaAdapter(sintomasSelecionados, TipoSintomaAdapter.DELETAR));
                mProgress.dismiss();
            }
        });

        btnFinalizarConsulta = (Button) view.findViewById(R.id.btn_finalizar_consulta);
        btnFinalizarConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalizarConsultaDialog confirmarDialog = new FinalizarConsultaDialog();

                Bundle bundle = new Bundle();
                bundle.putAll(getArguments());
                bundle.putString("descricao", txtDescricao.getText().toString());
                bundle.putString("horaInicio", horaInicio);
                bundle.putString("horaTermino", StringUtil.getHoraEMinuto(Calendar.getInstance().getTime()));

                confirmarDialog.setArguments(bundle);
                confirmarDialog.show(homeActivity.getFragmentManager(), "missiles");
            }
        });
        return view;
    }

    public List<Sintoma> getSintomasSelecionados() {
        return sintomasSelecionados;
    }

    public void setSintomasSelecionados(List<Sintoma> sintomasSelecionados) {
        this.sintomasSelecionados = sintomasSelecionados;
    }

    public SintomaAdapter getSintomaSelecionadosAdapter() {
        return sintomaSelecionadosAdapter;
    }

    public void setSintomaSelecionadosAdapter(SintomaAdapter sintomaSelecionadosAdapter) {
        this.sintomaSelecionadosAdapter = sintomaSelecionadosAdapter;
    }

}

