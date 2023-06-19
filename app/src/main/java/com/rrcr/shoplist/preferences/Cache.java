package com.rrcr.shoplist.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rrcr.shoplist.pojos.Producto;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

public class Cache {
    private SharedPreferences preferences;

    public Cache(Context context) {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public void setProductoEncontrado(Producto productoEncontrado) {
        Gson gson = new Gson();
        String productoString = gson.toJson(productoEncontrado, Producto.class);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("producto", productoString);
        editor.apply();
        editor.commit();
    }

    public Producto getProductoEncontrado() {
        Gson gson = new Gson();
        Type type = new TypeToken<Producto>() {
        }.getType();
        return gson.fromJson(preferences.getString("producto", ""), type);
    }

}
