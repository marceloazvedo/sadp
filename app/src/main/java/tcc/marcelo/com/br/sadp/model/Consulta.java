package tcc.marcelo.com.br.sadp.model;

import java.util.Calendar;

/**
 * Created by Marcelo S. de Azevedo on 06/10/2017.
 */
public class Consulta {

    private Long id;
    private Calendar dataConsulta;
    private Calendar horaInicio;
    private Calendar horaTermino;
    private String descricao;

    public Calendar getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Calendar dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Calendar horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Calendar getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(Calendar horaTermino) {
        this.horaTermino = horaTermino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
