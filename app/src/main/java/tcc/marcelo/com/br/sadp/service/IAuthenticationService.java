package tcc.marcelo.com.br.sadp.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tcc.marcelo.com.br.sadp.dto.LoginDTO;
import tcc.marcelo.com.br.sadp.dto.UsuarioDTO;

/**
 * Created by marcelo on 31/10/17.
 */
public interface IAuthenticationService {

    @Headers({
            "Authorization: SADP123987"
    })
    @POST("/api/autenticar")
    Call<UsuarioDTO> login(@Body LoginDTO login);

}
