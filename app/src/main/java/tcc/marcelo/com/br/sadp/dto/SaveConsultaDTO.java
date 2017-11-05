package tcc.marcelo.com.br.sadp.dto;

import java.util.List;

/**
 * Created by marcelo on 04/11/17.
 */

public class SaveConsultaDTO {

    private String descricao;
    private String horaInicio;
    private String horaTermino;
    private List<String> caracteristicasEncontradas;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }

    public List<String> getCaracteristicasEncontradas() {
        return caracteristicasEncontradas;
    }

    public void setCaracteristicasEncontradas(List<String> caracteristicasEncontradas) {
        this.caracteristicasEncontradas = caracteristicasEncontradas;
    }
}
