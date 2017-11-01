package tcc.marcelo.com.br.sadp.dto;

import java.util.List;

/**
 * Created by marcelo on 01/11/2017.
 */
public class CaracteristicasResponse extends DefaultReponse {

    private List<CaracteristicaDTO> caracteristicas;

    public List<CaracteristicaDTO> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaDTO> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
