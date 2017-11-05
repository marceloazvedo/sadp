package tcc.marcelo.com.br.sadp.singleton;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.model.Sintoma;
import tcc.marcelo.com.br.sadp.view.adapter.SintomaAdapter;

/**
 * Created by marcelo on 03/11/17.
 */
public class GerenciadorSelecaoSintomas {

    private static GerenciadorSelecaoSintomas gerenciadorSelecaoSintomas = null;
    private static List<Sintoma> sintomasSelecionados;

    private GerenciadorSelecaoSintomas(){
    }

    public static GerenciadorSelecaoSintomas getInstance(){
        if(gerenciadorSelecaoSintomas==null){
            gerenciadorSelecaoSintomas = new GerenciadorSelecaoSintomas();
            sintomasSelecionados = new ArrayList<>();
        }
        return gerenciadorSelecaoSintomas;
    }

    public static void add(Sintoma sintoma){
        GerenciadorSelecaoSintomas.getInstance().sintomasSelecionados.add(sintoma);
    }

    public static void remover(Sintoma sintoma){
        GerenciadorSelecaoSintomas.getInstance().sintomasSelecionados.remove(sintoma);
    }

    public static List<Sintoma> getSintomas(){
        return GerenciadorSelecaoSintomas.getInstance().sintomasSelecionados;
    }

}
