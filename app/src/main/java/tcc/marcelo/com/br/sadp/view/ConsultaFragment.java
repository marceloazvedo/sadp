package tcc.marcelo.com.br.sadp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tcc.marcelo.com.br.sadp.R;

/**
 * Created by GATI on 09/10/2017.
 */

public class ConsultaFragment extends MyFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.home_fragment, container, false);
        ((HomeActivity) getActivity()).getSupportActionBar().setTitle("SADP");
        HomeActivity mainActivity =(HomeActivity) getActivity();

        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return "ConsultaFragment";
    }
}
