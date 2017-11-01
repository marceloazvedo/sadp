package tcc.marcelo.com.br.sadp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.model.Sintoma;
import tcc.marcelo.com.br.sadp.view.tab.FinalizarConsultaTab;
import tcc.marcelo.com.br.sadp.view.tab.SelecaoSintomaTab;

/**
 * Created by marcelo on 10/10/17.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;
    private List<Sintoma> sintomas;
    private List<Sintoma> sintomasSelecionados;

    public MyPagerAdapter(FragmentManager fm, int numOfTabs, List<Sintoma> sintomas, List<Sintoma> sintomasSelecionados) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.sintomas = sintomas;
        this.sintomasSelecionados = sintomasSelecionados;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                SelecaoSintomaTab sintomaTab = new SelecaoSintomaTab();
                sintomaTab.setSintomas(sintomas);
                return sintomaTab;
            }
            case 1:
                FinalizarConsultaTab finalizarConsultaTab = new FinalizarConsultaTab();
                finalizarConsultaTab.setSintomasSelecionados(sintomasSelecionados);
                return finalizarConsultaTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    public List<Sintoma> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<Sintoma> sintomas) {
        this.sintomas = sintomas;
    }

    public List<Sintoma> getSintomasSelecionados() {
        return sintomasSelecionados;
    }

    public void setSintomasSelecionados(List<Sintoma> sintomasSelecionados) {
        this.sintomasSelecionados = sintomasSelecionados;
    }

}
