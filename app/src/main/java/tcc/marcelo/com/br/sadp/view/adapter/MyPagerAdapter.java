package tcc.marcelo.com.br.sadp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import tcc.marcelo.com.br.sadp.view.tab.FinalizarConsultaTab;
import tcc.marcelo.com.br.sadp.view.tab.SelecaoSintomaTab;

/**
 * Created by marcelo on 10/10/17.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public MyPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SelecaoSintomaTab();
            case 1:
                return new FinalizarConsultaTab();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
