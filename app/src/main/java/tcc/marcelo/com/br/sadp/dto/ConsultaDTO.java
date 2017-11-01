package tcc.marcelo.com.br.sadp.dto;

import java.util.List;

/**
 * Created by marcelo on 01/11/2017.
 */

public class ConsultaDTO {

    private String _id;
    private String descricao;
    private String horaInicio;
    private String horaTermino;
    private List<CaracteristicaDTO> caracteristicasEncontradas;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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

    public List<CaracteristicaDTO> getCaracteristicasEncontradas() {
        return caracteristicasEncontradas;
    }

    public void setCaracteristicasEncontradas(List<CaracteristicaDTO> caracteristicasEncontradas) {
        this.caracteristicasEncontradas = caracteristicasEncontradas;
    }
}
