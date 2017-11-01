package tcc.marcelo.com.br.sadp.dto;

import java.util.List;

/**
 * Created by marcelo on 01/11/2017.
 */
public class ConsultasResponse extends DefaultReponse {

    private List<ConsultaDTO> consultas;

    public List<ConsultaDTO> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<ConsultaDTO> consultas) {
        this.consultas = consultas;
    }
}
