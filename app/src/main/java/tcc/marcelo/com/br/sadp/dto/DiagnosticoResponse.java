package tcc.marcelo.com.br.sadp.dto;

import java.util.List;

/**
 * Created by marcelo on 05/11/17.
 */

public class DiagnosticoResponse extends DefaultReponse {

    private String paciente;
    private List<EsquizofreniaDiagnosticoDTO> diagnostico;

    public List<EsquizofreniaDiagnosticoDTO> getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(List<EsquizofreniaDiagnosticoDTO> diagnosticos) {
        this.diagnostico = diagnosticos;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
}
