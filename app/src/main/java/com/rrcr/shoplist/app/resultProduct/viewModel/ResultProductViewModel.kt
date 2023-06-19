package com.rrcr.shoplist.app.resultProduct.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rrcr.shoplist.app.api.FindProductApi
import com.rrcr.shoplist.pojos.Producto
import kotlinx.coroutines.launch

class ResultProductViewModel : ViewModel() {
    var productSoriana = MutableLiveData<Producto?>()
    var productAmazon = MutableLiveData<Producto?>()
    var productWalmart = MutableLiveData<Producto?>()

    fun getData(code: String) {
        viewModelScope.launch {
            productSoriana.postValue(FindProductApi.getProductBySoriana(code))
            productAmazon.postValue(FindProductApi.getProductByAmazon(code))
            productWalmart.postValue(FindProductApi.getProductByWalmartGT(code))
        }
    }
}
