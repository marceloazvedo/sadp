package tcc.marcelo.com.br.sadp.parser;

import java.util.ArrayList;
import java.util.List;

import tcc.marcelo.com.br.sadp.dto.PacienteDTO;
import tcc.marcelo.com.br.sadp.model.Paciente;
import tcc.marcelo.com.br.sadp.util.StringUtil;

/**
 * Created by marcelo on 01/11/2017.
 */

public class PacienteParser {

    public static Paciente parser(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setId(dto.get_id());
        try {
            paciente.setDataNascimento(StringUtil.getData(StringUtil.getDataJSON(dto.getDataNascimento())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        paciente.setDescricao(dto.getDescricao());
        try {
            paciente.setDataEntrada(StringUtil.getData(StringUtil.getDataJSON(dto.getDataEntrada())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        paciente.setNome(dto.getNome());
        return paciente;
    }

    public static List<Paciente> parserList(List<PacienteDTO> dtoList) {
        List<Paciente> consultas = new ArrayList<>();
        for (PacienteDTO dto : dtoList) {
            consultas.add(parser(dto));
        }
        return consultas;
    }
}
