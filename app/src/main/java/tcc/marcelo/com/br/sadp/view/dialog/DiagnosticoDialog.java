package tcc.marcelo.com.br.sadp.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.view.DiagnosticoFragment;
import tcc.marcelo.com.br.sadp.view.HomeActivity;

/**
 * Created by marcelo on 05/11/17.
 */

public class DiagnosticoDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String mensagem = getResources().getString(R.string.buscar_diagnostico);
        builder.setMessage(mensagem)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DiagnosticoFragment diagnosticoFragment = new DiagnosticoFragment();
                        Bundle bundle = new Bundle();
                        bundle.putAll(getArguments());
                        diagnosticoFragment.setArguments(bundle);
                        FragmentManagerUtil.trocarFragment(getActivity(), diagnosticoFragment);
                    }
                })
                .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        return builder.create();
    }
}
