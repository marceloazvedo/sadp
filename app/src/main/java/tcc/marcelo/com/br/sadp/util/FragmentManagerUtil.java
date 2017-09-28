package tcc.marcelo.com.br.sadp.util;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import java.util.List;

import tcc.marcelo.com.br.sadp.R;
import tcc.marcelo.com.br.sadp.view.MyFragment;

/**
 * Created by Marcelo S. de Azevedo on 26/09/2017.
 */
public class FragmentManagerUtil {

    private static FragmentManager fragmentManager = null;
    private static FragmentTransaction fragmentTransaction = null;
    private static String oldFragmentTag = null;

    public static void trocarFragment(Activity activity, MyFragment nova) {
        fragmentManager = activity.getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        if (oldFragmentTag != null) {
            Fragment fragment = fragmentManager.findFragmentByTag(oldFragmentTag);
            fragmentTransaction.remove(fragment);
        }
        fragmentTransaction.add(R.id.frame_layout, nova, nova.getFragmentTag());
        oldFragmentTag = nova.getFragmentTag();
        fragmentTransaction.commit();
    }
}
