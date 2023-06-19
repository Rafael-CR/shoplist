package com.rrcr.shoplist.retrofit;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String URL, OkHttpClient client) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client =
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    public static ApiInterface getService(String url) {
        OkHttpClient client = getOkHttpClient();
        retrofit2.Retrofit retrofit = getClient(url, client);
        return retrofit.create(ApiInterface.class);
    }

    public static OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = null;
        try {

//            SSLContext sslContext = obtenerCertificadoSEFIN(context);

            okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Chain chain) throws IOException {
                            Request.Builder newRequest = chain.request().newBuilder();
                            newRequest.addHeader("deviceplatform", "android")
                                    .removeHeader("User-Agent")
                                    .addHeader("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0");
                            return chain.proceed(newRequest.build());
                        }
                    }).connectTimeout(80, TimeUnit.SECONDS)
                    .writeTimeout(80, TimeUnit.SECONDS)
                    .readTimeout(80, TimeUnit.SECONDS)
//                    .sslSocketFactory(sslContext.getSocketFactory(), x509TrustManager)
                    .build();
        } catch (Exception e) {

        }
        return okHttpClient;
    }

}
