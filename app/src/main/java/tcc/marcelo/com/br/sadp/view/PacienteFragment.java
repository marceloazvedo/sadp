package tcc.marcelo.com.br.sadp.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.util.Mask;

/**
 * Created by Marcelo S. de Azevedo on 28/09/2017.
 */
public class PacienteFragment extends MyFragment {

    private HomeActivity mainActivity = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.paciente_fragment, container, false);
        mainActivity = ((HomeActivity) getActivity());

        mainActivity.getSupportActionBar().setTitle("CADASTRAR PACIENTE");
        mainActivity.setDrawerState(false);

        EditText txtNome = (EditText) fragment.findViewById(R.id.txt_nome_paciente);
        EditText txtDataEntrada = (EditText) fragment.findViewById(R.id.txt_data_entrada);
        txtDataEntrada.addTextChangedListener(Mask.insert("##/##/####", txtDataEntrada));
        EditText txtDataNascimento = (EditText) fragment.findViewById(R.id.txt_data_nascimento);
        txtDataNascimento.addTextChangedListener(Mask.insert("##/##/####", txtDataNascimento));
        EditText txtDescricao = (EditText) fragment.findViewById(R.id.txt_descricao_paciente);
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
