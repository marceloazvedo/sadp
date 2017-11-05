package tcc.marcelo.com.br.sadp.util;

import android.app.Activity;
import android.content.Intent;

import tcc.marcelo.com.br.sadp.view.LoginActivity;

/**
 * Created by marcelo on 03/11/17.
 */

public class ActivityUtil {

    public static void logout(Activity activity){
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(activity);
        sharedPreferencesUtil.limpar();
        Intent startLoginActivity = new Intent(activity, LoginActivity.class);
        startLoginActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.finishAffinity();
        activity.startActivity(startLoginActivity);
    }
}
