package tcc.marcelo.com.br.sadp.parser;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.dto.ConsultaDTO;
import tcc.marcelo.com.br.sadp.model.Consulta;
import tcc.marcelo.com.br.sadp.util.StringUtil;

/**
 * Created by marcelo on 01/11/2017.
 */
public class ConsultaParser {

    public static Consulta parser(ConsultaDTO dto){
        Consulta consulta = new Consulta();
        consulta.setId(dto.get_id());
        consulta.setDescricao(dto.getDescricao());
        // consulta.setDataConsulta(StringUtil.getData(dto.get));
        consulta.setHoraInicio(dto.getHoraInicio());
        consulta.setHoraTermino(dto.getHoraTermino());
        return consulta;
    }

    public static List<Consulta> parserList(List<ConsultaDTO> dtoList) {
        List<Consulta> consultas = new ArrayList<>();
        for(ConsultaDTO dto : dtoList){
            consultas.add(parser(dto));
        }
        return consultas;
    }

}
