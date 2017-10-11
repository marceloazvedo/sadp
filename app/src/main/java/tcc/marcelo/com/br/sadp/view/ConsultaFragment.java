package tcc.marcelo.com.br.sadp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.util.FragmentManagerUtil;
import tcc.marcelo.com.br.sadp.view.adapter.MyPagerAdapter;

/**
 * Created by GATI on 09/10/2017.
 */

public class ConsultaFragment extends MyFragment {

    private HomeActivity homeActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View fragment = inflater.inflate(R.layout.consulta_fragment, container, false);
        ((HomeActivity) getActivity()).getSupportActionBar().setTitle("SADP");

        homeActivity =(HomeActivity) getActivity();
        homeActivity.setDrawerState(false);
        homeActivity.supportInvalidateOptionsMenu();
        TabLayout tabLayout = (TabLayout) fragment.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("ADD SINTOMAS"));
        tabLayout.addTab(tabLayout.newTab().setText("FINALIZAR"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) fragment.findViewById(R.id.pager);

        MyPagerAdapter adapter = new MyPagerAdapter(homeActivity.getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return "ConsultaFragment";
    }
}
