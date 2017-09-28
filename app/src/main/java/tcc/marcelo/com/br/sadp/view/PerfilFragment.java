package tcc.marcelo.com.br.sadp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tcc.marcelo.com.br.sadp.R;

/**
 * Created by Marcelo S. de Azevedo on 27/09/2017.
 */

public class PerfilFragment extends MyFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.perfil_fragment, container, false);
        ((HomeActivity) getActivity()).getSupportActionBar().setTitle("PERFIL");
        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return "PerfilFragment";
    }
}
