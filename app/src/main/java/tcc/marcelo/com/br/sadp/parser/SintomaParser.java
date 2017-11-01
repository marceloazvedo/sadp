package tcc.marcelo.com.br.sadp.parser;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.dto.CaracteristicaDTO;
import tcc.marcelo.com.br.sadp.model.Sintoma;

/**
 * Created by marcelo on 01/11/2017.
 */

public class SintomaParser {

    public static Sintoma parser(CaracteristicaDTO dto){
        Sintoma sintoma = new Sintoma();
                sintoma.setId(dto.get_id());
        sintoma.setDescrica(dto.getDescricao());
        return sintoma;
    }

    public static List<Sintoma> parserList(List<CaracteristicaDTO> dtoList) {
        List<Sintoma> consultas = new ArrayList<>();
        for(CaracteristicaDTO dto : dtoList){
            consultas.add(parser(dto));
        }
        return consultas;
    }
}
