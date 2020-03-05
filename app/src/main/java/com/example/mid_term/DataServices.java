package com.example.mid_term;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataServices {


    @GET("E14trR2lD")
    Call<weather> getAllPokemons();

}
