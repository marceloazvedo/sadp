package tcc.marcelo.com.br.sadp.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tcc.marcelo.com.br.sadp.R;

/**
 * Created by Marcelo S. de Azevedo on 26/09/2017.
 */

public class HomeFragment extends MyFragment {

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
        return "HomeFragment";
    }
}
