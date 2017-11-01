package tcc.marcelo.com.br.sadp.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.view.HomeActivity;

/**
 * Created by marcelo on 01/11/2017.
 */

public class ErroConexaoDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String mensagem = getResources().getString(R.string.erro_conexao);
        builder.setMessage(mensagem)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }
}
