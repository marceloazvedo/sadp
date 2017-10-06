package tcc.marcelo.com.br.sadp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Marcelo S. de Azevedo on 06/10/2017.
 */

public class StringUtil {

    public static String getData(Calendar calendar){
        return new SimpleDateFormat("dd/MM/YYYY").format(calendar.getTime());
    }

    public static String getHora(Calendar calendar){
        return new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
    }
}
