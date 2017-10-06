package tcc.marcelo.com.br.sadp.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.view.HomeActivity;

/**
 * Created by GATI on 06/10/2017.
 */

public class DeletarPacienteDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ;
        String mensagem = String.format(getResources().getString(R.string.deseja_deletar_paciente), ((HomeActivity) getActivity()).getPaciente().getNome());
        builder.setMessage(mensagem)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FragmentManagerUtil.popBackStack(getActivity());
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
