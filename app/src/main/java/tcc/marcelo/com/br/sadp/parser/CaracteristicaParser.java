package tcc.marcelo.com.br.sadp.parser;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.model.Sintoma;

/**
 * Created by marcelo on 04/11/17.
 */

public class CaracteristicaParser {

    public static List<String> getListaCaractersiticasID(List<Sintoma> sintomas){
        List<String> caracteristicas = new ArrayList<>();
        for(Sintoma sintoma : sintomas){
            caracteristicas.add(sintoma.getId());
        }
        return caracteristicas;
    }

}
