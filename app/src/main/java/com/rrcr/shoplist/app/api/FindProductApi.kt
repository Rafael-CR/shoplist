package com.rrcr.shoplist.app.api

import com.rrcr.shoplist.generics.Utils
import com.rrcr.shoplist.pojos.Producto
import com.rrcr.shoplist.retrofit.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

class FindProductApi {
    companion object {
        @JvmStatic
        suspend fun getProductBySurtiTienda(code: String): Producto? {
            return withContext(Dispatchers.IO) {
                val response = Api.getService(URL_SURTITIENDA).getInfoProductsByMarket(code)
                handleResponse(response.execute(), SURTITIENDA)
            }
        }

        @JvmStatic
        suspend fun getProductByAmazon(code: String): Producto? {
            return withContext(Dispatchers.IO) {
                val response = Api.getService(URL_AMAZON).getInfoProductsByAmazon(code)
                handleResponse(response.execute(), AMAZON)
            }
        }

        @JvmStatic
        suspend fun getProductBySoriana(code: String): Producto? {
            return withContext(Dispatchers.IO) {
                val response = Api.getService(URL_SORIANA).getInfoProductsBySoriana(code)
                handleResponse(response.execute(), SORIANA)
//                Utils.loadSorianaPage(URL_SORIANA + "buscar?q=$code")
            }
        }

        @JvmStatic
        suspend fun getProductByWalmartGT(code: String): Producto? {
            return withContext(Dispatchers.IO) {
                val response = Api.getService(URL_WALMARTGT).getInfoProductsByMarket(code)
                handleResponse(response.execute(), WALMART)
            }
        }

        private fun handleResponse(response: Response<ResponseBody?>, market: String): Producto? {
            return when (response.code()) {
                200 -> {
                    parseProductByMarket(response, market)
                }

                404 -> {
                    null
                }

                else -> {
                    null
                }
            }
        }

        private fun parseProductByMarket(
            response: Response<ResponseBody?>,
            market: String,
        ): Producto? {
            return when (market) {
                WALMART -> {
                    Utils.parseWalmart(response.body()?.string())
                }

                AMAZON -> {
                    Utils.parseAmazon(response.body()?.string())
                }

                SURTITIENDA -> {
                    Utils.parseSurtiTienda(response.body()?.string())
                }

                SORIANA -> {
                    Utils.parseSoriana(response.body()?.string())
                }

                else -> {
                    null
                }
            }
        }

        const val URL_SURTITIENDA = "https://www.surtitienda.mx/"
        const val URL_AMAZON = "https://www.amazon.com.mx/"
        const val URL_WALMARTGT = "https://www.walmart.com.gt/"
        const val URL_SORIANA = "https://www.soriana.com/"

        const val WALMART = "walmart"
        const val AMAZON = "amazon"
        const val SURTITIENDA = "surtitienda"
        const val SORIANA = "soriana"
    }
}
