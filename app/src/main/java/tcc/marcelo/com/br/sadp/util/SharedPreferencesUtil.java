package tcc.marcelo.com.br.sadp.util;

import android.content.Context;
import android.content.SharedPreferences;

import tcc.marcelo.com.br.sadp.view.LoginActivity;

/**
 * Created by Marcelo S. de Azevedo on 26/09/2017.
 */
public class SharedPreferencesUtil {

    private Context context = null;
    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    private static final String SHARED_PREFERENCES_APP = "sadp_prefecences";
    public static final String TOKEN = "token";

    public SharedPreferencesUtil(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_APP, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void addString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key){
        return sharedPreferences.getString(key, null);
    }

    public String getTokenApp() {
        return getString(TOKEN);
    }

    public void limpar() {
        editor.clear();
        editor.commit();
    }
}
