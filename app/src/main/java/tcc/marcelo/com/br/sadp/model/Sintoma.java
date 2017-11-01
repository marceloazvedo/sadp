package tcc.marcelo.com.br.sadp.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by marcelo on 10/10/17.
 */
public class Sintoma extends RealmObject implements Serializable {

    @PrimaryKey @Required
    private String id;
    private String descrica;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescrica() {
        return descrica;
    }

    public void setDescrica(String descrica) {
        this.descrica = descrica;
    }
}
