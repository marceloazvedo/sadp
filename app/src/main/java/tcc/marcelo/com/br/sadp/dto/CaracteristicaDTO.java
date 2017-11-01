package tcc.marcelo.com.br.sadp.dto;

/**
 * Created by marcelo on 01/11/2017.
 */

public class CaracteristicaDTO {

    private String _id;
    private String descricao;

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
}
