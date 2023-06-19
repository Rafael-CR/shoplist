package com.rrcr.shoplist.scanner;

import android.content.Context;

import com.rrcr.shoplist.generics.Utils;
import com.rrcr.shoplist.pojos.Producto;
import com.rrcr.shoplist.preferences.Cache;
import com.rrcr.shoplist.retrofit.Api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScannerApiClient {

    public static void getInfoProductoComer(final String codigo, final Context context, final IProductoResultado.View view) {
//        String url = "https://www.google.com";
        String url = "https://www.lacomer.com.mx/";

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("ver", "detallearticulo");
        queryMap.put("opcion", "detarticulo");
        queryMap.put("origen", "artipasillo");
        queryMap.put("jsp", "PasilloPadre.jsp");
        queryMap.put("pasId", "93");
        queryMap.put("noPagina", "1");
        final Call<ResponseBody> response = Api.getService(url).getInfoProductosComer(codigo, queryMap);
        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                handleResponse(response);
                try {
                    if (response.body() != null) {
                        Producto producto = Utils.parseSurtiTienda(response.body().string());
                        Cache cache = new Cache(context);
                        if (producto != null) {
                            producto.setBarCode(codigo);
                            cache.setProductoEncontrado(producto);
                            view.setDatosProducto();
                        } else {
                            view.noDataFound();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    private static void handleResponse(Response<ResponseBody> response) {
    }
}
