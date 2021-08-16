package com.example.projetocomviewmodel.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofitProduct = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getProductsServices(): ServiceProduct {
        return retrofitProduct.create(ServiceProduct::class.java)

    }

}