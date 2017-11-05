package tcc.marcelo.com.br.sadp.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import tcc.marcelo.com.br.sadp.dto.CaracteristicasResponse;
import tcc.marcelo.com.br.sadp.dto.ConsultaDTO;
import tcc.marcelo.com.br.sadp.dto.ConsultasResponse;
import tcc.marcelo.com.br.sadp.dto.DefaultReponse;
import tcc.marcelo.com.br.sadp.dto.DiagnosticoResponse;
import tcc.marcelo.com.br.sadp.dto.PacienteDTO;
import tcc.marcelo.com.br.sadp.dto.PacientesRespone;
import tcc.marcelo.com.br.sadp.dto.SaveConsultaDTO;

/**
 * Created by marcelo on 01/11/2017.
 */
public interface IPsiquiatraService {

    @GET("/api/psiquiatra/paciente")
    Call<PacientesRespone> getPacientes(@Header("Authorization") String token);

    @GET("/api/psiquiatra/consulta/{idPaciente}")
    Call<ConsultasResponse> getConsultas(@Header("Authorization") String token, @Path("idPaciente") String idPaciente);

    @GET("/api/psiquiatra/caracteristica")
    Call<CaracteristicasResponse> getCaracteristicas(@Header("Authorization") String token);

    @POST("/api/psiquiatra/paciente")
    Call<DefaultReponse> cadastrarPaciente(@Header("Authorization") String token, @Body PacienteDTO paciente);

    @DELETE("/api/psiquiatra/paciente/{idPaciente}")
    Call<DefaultReponse> removerPaciente(@Header("Authorization") String token, @Path("idPaciente") String idPaciente);

    @POST("/api/psiquiatra/consulta/{idPaciente}")
    Call<DefaultReponse> cadastrarConsulta(@Header("Authorization") String token, @Path("idPaciente") String idPaciente, @Body SaveConsultaDTO paciente);

    @GET("/api/psiquiatra/diagnostico/{idPaciente}")
    Call<DiagnosticoResponse> diagnotico(@Header("Authorization") String token, @Path("idPaciente") String idPaciente);

}
