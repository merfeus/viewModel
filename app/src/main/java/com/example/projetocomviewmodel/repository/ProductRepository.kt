package com.example.projetocomviewmodel.repository

import com.example.projetocomviewmodel.model.Products
import com.example.projetocomviewmodel.service.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//substitui pela chamada do callback no inicio da view (fragment activity)
//antigo by lazy
//repositorio conversa com API

class ProductRepository {

    private val service = RetrofitBuilder.getProductsServices()

    fun getProducts(callBack: (List<Products>?, String?) -> Unit) {

        val call = service.getProducts()
        call.enqueue(object : Callback<List<Products>> {

            override fun onResponse(call: Call<List<Products>>, response: Response<List<Products>>) {
                response.body()?.let {
                    callBack(it, null)
                    callBack(null, "Sucesso!!!!!!")
                }
                if (response.code() != 200) {
                    callBack(null, "Algum erro desconhecido")
                }
            }

            override fun onFailure(call: Call<List<Products>>, t: Throwable) {
                callBack(null, t.localizedMessage)
            }
        })
    }


}