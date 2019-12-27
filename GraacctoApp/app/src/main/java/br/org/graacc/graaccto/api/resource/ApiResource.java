package br.org.graacc.graaccto.api.resource;

import java.util.List;

import br.org.graacc.graaccto.api.dto.RegisterDTO;
import br.org.graacc.graaccto.core.Constants;
import br.org.graacc.graaccto.domain.Donation;
import br.org.graacc.graaccto.domain.Thankful;
import br.org.graacc.graaccto.domain.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiResource {

    @GET(value = Constants.API_LOGAR)
    Call<User> signin(@Query("login") String login, @Query("senha") String senha);

    @POST(value = Constants.API_SALVAR)
    Call<User> signup(@Body User user);

    @GET(value = Constants.API_GRATO)
    Call<List<RegisterDTO>> getGratos(@Query("id") Integer id);

    @GET(value = Constants.API_DOACAO)
    Call<List<RegisterDTO>> getDoacoes(@Query("id") Integer id);

    @POST(value = Constants.API_GRATO_SALVAR)
    Call<Thankful> salvarGrato(@Body Thankful grato);

    @POST(value = Constants.API_DOACAO_SALVAR)
    Call<Donation> salvarDoacao(@Body Donation donation);

}
