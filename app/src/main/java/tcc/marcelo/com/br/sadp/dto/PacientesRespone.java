package tcc.marcelo.com.br.sadp.dto;

import java.util.List;

/**
 * Created by marcelo on 01/11/2017.
 */

public class PacientesRespone extends DefaultReponse {

    private List<PacienteDTO> pacientes;

    public List<PacienteDTO> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<PacienteDTO> pacientes) {
        this.pacientes = pacientes;
    }
}
