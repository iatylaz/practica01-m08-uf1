package com.example.newrecycler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MarcasApi {
    @GET("v3/4c13153e-9913-4095-9f94-8039a5216d37")
    Call<List<Marcas>> getMarcas();

}
