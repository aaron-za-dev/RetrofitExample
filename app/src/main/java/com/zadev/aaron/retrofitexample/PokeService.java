package com.zadev.aaron.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeService {

    @GET("pokemon")
    Call<MyResponse> response(@Query("limit") int limit, @Query("offset") int offset);
}
