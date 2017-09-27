package tcc.marcelo.com.br.sadp.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tcc.marcelo.com.br.sadp.R;

/**
 * Created by GATI on 26/09/2017.
 */

public class HomeFragment extends MyFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.home_fragment, container, false);
        Button btnAction = (Button) fragment.findViewById(R.id.btnAction);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Essa krai deu certo!", Snackbar.LENGTH_LONG).show();
            }
        });
        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return "HomeFragment";
    }
}
