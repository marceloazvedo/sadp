package tcc.marcelo.com.br.sadp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;

/**
 * Created by Marcelo S. de Azevedo on 27/09/2017.
 */

public class PerfilFragment extends MyFragment {

    private HomeActivity homeActivity = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View fragment = inflater.inflate(R.layout.perfil_fragment, container, false);
        Button btnCadastrarPaciente = (Button) fragment.findViewById(R.id.btnCadastrarPaciente);

        btnCadastrarPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aqui deve ser feito o cadastramento do paciente no web service
                FragmentManagerUtil.popBackStack(PerfilFragment.this.homeActivity);
            }
        });

        homeActivity = (HomeActivity) getActivity();
        homeActivity.getSupportActionBar().setTitle("PERFIL");
        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return "PerfilFragment";
    }
}
