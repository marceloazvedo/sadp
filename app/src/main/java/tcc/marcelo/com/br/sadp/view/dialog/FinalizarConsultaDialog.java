package tcc.marcelo.com.br.sadp.view.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.view.ConsultaFragment;
import tcc.marcelo.com.br.sadp.view.HomeActivity;
import tcc.marcelo.com.br.sadp.view.VisualizarPacienteFragment;

/**
 * Created by marcelo on 11/10/17.
 */

public class FinalizarConsultaDialog extends DialogFragment {

    private HomeActivity homeActivity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.deseja_finalizar_consulta)
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        homeActivity = (HomeActivity) getActivity();
                        FragmentManagerUtil.popBackStack(getActivity());
                        // Resolver bug do voltar volar
                        FragmentManagerUtil.trocarFragment(getActivity(), new VisualizarPacienteFragment());
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
