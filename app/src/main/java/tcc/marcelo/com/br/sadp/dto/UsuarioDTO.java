package tcc.marcelo.com.br.sadp.dto;

/**
 * Created by marcelo on 31/10/17.
 */
public class UsuarioDTO extends DefaultReponse {

    private String nome;
    private String tipoUsuario;
    private String token;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
