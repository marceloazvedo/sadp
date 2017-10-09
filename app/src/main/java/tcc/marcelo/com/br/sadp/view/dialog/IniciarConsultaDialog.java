package tcc.marcelo.com.br.sadp.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.util.SharedPreferencesUtil;
import tcc.marcelo.com.br.sadp.view.LoginActivity;

/**
 * Created by Marcelo S. de Azevedo on 09/10/2017.
 */
public class IniciarConsultaDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.iniciar_nova_consulta)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
