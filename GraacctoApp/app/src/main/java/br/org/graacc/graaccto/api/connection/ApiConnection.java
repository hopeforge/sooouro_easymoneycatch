package br.org.graacc.graaccto.api.connection;

import br.org.graacc.graaccto.api.resource.ApiResource;
import br.org.graacc.graaccto.core.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnection {

    private static Retrofit instance;

    public static ApiResource getConnection() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(Constants.API_URL_EXTERNA)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance.create(ApiResource.class);
    }
}
