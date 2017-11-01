package tcc.marcelo.com.br.sadp.dto;

/**
 * Created by marcelo on 01/11/17.
 */

public class LoginDTO {

    private String login;
    private String senha;

    public LoginDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
