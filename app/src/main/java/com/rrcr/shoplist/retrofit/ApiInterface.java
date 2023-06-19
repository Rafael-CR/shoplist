package com.rrcr.shoplist.retrofit;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("/{codigo}")
    Call<ResponseBody> getInfoProductsByMarket(@Path("codigo") String codigo);

    @GET("/s?")
    Call<ResponseBody> getInfoProductsByAmazon(@Query("k") String code);

    @Headers("Accept: */*")
    @GET("/buscar")
    Call<ResponseBody> getInfoProductsBySoriana(@Query("q") String code);

    @GET("lacomer/doHome.action?")
    Call<ResponseBody> getInfoProductosComer(@Query("artEan") String artEan, @QueryMap Map<String, String> opciones);

}
