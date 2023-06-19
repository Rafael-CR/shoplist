package com.rrcr.shoplist.app.resultProduct.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rrcr.shoplist.app.resultProduct.viewModel.ResultProductViewModel
import com.rrcr.shoplist.databinding.ActivityProductoResultadoBinding
import com.rrcr.shoplist.pojos.Producto
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class ResultProduct : AppCompatActivity() {
    private lateinit var binding: ActivityProductoResultadoBinding
    private var code: String? = null
    private val viewModel: ResultProductViewModel = ResultProductViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductoResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        code = intent.getStringExtra(CODE)
        code?.let { viewModel.getData(it) }
        binding.codeValue.text = code
        initObservers()
    }

    private fun initObservers() {
        viewModel.productSoriana.observe(this) { product ->
            if (product != null) {
                setDatosProductSoriana(product)
            } else {
                noDataFoundSoriana()
            }
        }

        viewModel.productAmazon.observe(this) { product ->
            if (product != null) {
                setDatosProductAmazon(product)
            } else {
                noDataFoundAmazon()
            }
        }

        viewModel.productWalmart.observe(this) { product ->
            if (product != null) {
                setDatosProductWalmart(product)
            } else {
                noDataFoundWalmart()
            }
        }
    }

    private fun setDatosProductSoriana(product: Producto) {
        setInfoScreen(INFO_SCREEN)
        binding.nombreSoriana.text = (product.nombre)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val newRequest = chain.request().newBuilder()
                    newRequest.addHeader("Accept", "*/*")
                    return chain.proceed(newRequest.build())
                }
            })
            .build()
        Glide.with(this).load(product.urlImage).centerCrop().into(binding.imagenResultSoriana)
        binding.nombreSoriana.visibility = View.VISIBLE
        binding.imagenResultSoriana.visibility = View.VISIBLE
        binding.loadSoriana.visibility = View.GONE
    }

    private fun setDatosProductAmazon(product: Producto) {
        setInfoScreen(INFO_SCREEN)
        binding.nombreAmazon.text = (product.nombre)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val newRequest = chain.request().newBuilder()
                    newRequest.addHeader("Accept", "*/*")
                    return chain.proceed(newRequest.build())
                }
            })
            .build()
        Glide.with(this).load(product.urlImage).centerCrop().into(binding.imagenResultAmazon)
        binding.nombreAmazon.visibility = View.VISIBLE
        binding.imagenResultAmazon.visibility = View.VISIBLE
        binding.loadAmazon.visibility = View.GONE
    }

    private fun setDatosProductWalmart(product: Producto) {
        setInfoScreen(INFO_SCREEN)
        binding.nombreWalmart.text = (product.nombre)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val newRequest = chain.request().newBuilder()
                    newRequest.addHeader("Accept", "*/*")
                    return chain.proceed(newRequest.build())
                }
            })
            .build()
        Glide.with(this).load(product.urlImage).centerCrop().into(binding.imagenResultWalmart)
        binding.nombreWalmart.visibility = View.VISIBLE
        binding.imagenResultWalmart.visibility = View.VISIBLE
        binding.loadWalmart.visibility = View.GONE
    }

    private fun noDataFoundSoriana() {
        setInfoScreen(INFO_SCREEN)
        binding.loadingTxtSoriana.text = ("No se ha encontrado información")
        binding.progresSoriana.visibility = View.INVISIBLE
    }

    private fun noDataFoundAmazon() {
        setInfoScreen(INFO_SCREEN)
        binding.loadingTxtAmazon.text = ("No se ha encontrado información")
        binding.progressAmazon.visibility = View.INVISIBLE
    }

    private fun noDataFoundWalmart() {
        setInfoScreen(INFO_SCREEN)
        binding.loadingTxtWalmart.text = ("No se ha encontrado información")
        binding.progressWalmart.visibility = View.INVISIBLE
    }

    private fun setInfoScreen(value: Int) {
        if (value == LOAD_SCREEN) {
            binding.loadScreen.visibility = (View.VISIBLE)
            binding.infoProducto.visibility = (View.GONE)
        } else {
            binding.loadScreen.visibility = (View.GONE)
            binding.infoProducto.visibility = (View.VISIBLE)
        }
    }

    companion object {
        const val CODE = "code_number"
        private const val LOAD_SCREEN = 1
        private const val INFO_SCREEN = 2
    }
}
