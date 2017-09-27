package tcc.marcelo.com.br.sadp.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Marcelo S. de Azevedo on 27/09/2017.
 */
public class Paciente implements Serializable {

    private Long id;
    private String nome;
    private String dataEntrada;
    private String dataNascimento;
    private String descricao;

    public Paciente(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente)) return false;

        Paciente paciente = (Paciente) o;

        return id.equals(paciente.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}