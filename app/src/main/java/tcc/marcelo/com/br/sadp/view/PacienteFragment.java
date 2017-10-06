package tcc.marcelo.com.br.sadp.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.util.Mask;

/**
 * Created by Marcelo S. de Azevedo on 28/09/2017.
 */
public class PacienteFragment extends MyFragment {

    private HomeActivity mainActivity;
    private Paciente paciente;
    private EditText txtNome;
    private EditText txtDataEntrada;
    private EditText txtDataNascimento;
    private EditText txtDescricao;
    private Button btnCadastrarAtualizarPaciente;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.paciente_fragment, container, false);
        mainActivity = ((HomeActivity) getActivity());
        paciente = mainActivity.getPaciente();

        mainActivity.getSupportActionBar().setTitle("CADASTRAR PACIENTE");
        mainActivity.setDrawerState(false);

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
        btnCadastrarAtualizarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paciente == null) {
                    FragmentManagerUtil.popBackStack(mainActivity);
                }
            }
        });
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainActivity.setDrawerState(true);
    }

    @Override
    public String getFragmentTag() {
        return "PacienteFragment";
    }
}
