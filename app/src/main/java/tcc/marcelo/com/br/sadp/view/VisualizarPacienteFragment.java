package tcc.marcelo.com.br.sadp.view;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Consulta;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.view.adapter.AtendimentoAdapter;
import tcc.marcelo.com.br.sadp.view.dialog.DeletarPacienteDialog;
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
    private BottomNavigationItemView novaConsulta;
    private BottomNavigationView bottomNavigation;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragment.getContext());
        atendimentoRecyclerView = (RecyclerView) fragment.findViewById(R.id.atendimento_rv);
        atendimentoRecyclerView.setLayoutManager(linearLayoutManager);
        List<Consulta> consultas = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Consulta consulta = new Consulta();
            consulta.setDescricao("TESTE, 1,2,3, TESTE!");
            consulta.setHoraTermino(Calendar.getInstance());
            consulta.setHoraInicio(Calendar.getInstance());
            consulta.setDataConsulta(Calendar.getInstance());
            consultas.add(consulta);
        }
        atendimentoRecyclerView.setAdapter(new AtendimentoAdapter(consultas));

        bottomNavigation = (BottomNavigationView) fragment.findViewById(R.id.bottom_navigation);
        novaConsulta = (BottomNavigationItemView) bottomNavigation.findViewById(R.id.menuitem_nova_consulta);
        novaConsulta.setOnClickListener(new View.OnClickListener() {
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
}
