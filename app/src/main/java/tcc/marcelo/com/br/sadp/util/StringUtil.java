package tcc.marcelo.com.br.sadp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Marcelo S. de Azevedo on 06/10/2017.
 */

public class StringUtil {

    public static String getData(Date date){
        return new SimpleDateFormat("dd/MM/YYYY").format(date.getTime());
    }

    public static String getHora(Date date){
        return new SimpleDateFormat("HH:mm:ss").format(date.getTime());
    }

    public static Date getData(String data) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(data);
    }

    public static Date getDataJSON(String data) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(data);
    }

}
