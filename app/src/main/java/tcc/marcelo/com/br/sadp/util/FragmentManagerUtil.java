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

    public static void trocarFragment(Activity activity, MyFragment nova) {
        fragmentManager = activity.getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame_layout, nova, nova.getFragmentTag());
        fragmentTransaction.commit();
    }

    public static void popBackStack(Activity activity) {
        fragmentManager = activity.getFragmentManager();
        fragmentManager.popBackStack();
    }
}
