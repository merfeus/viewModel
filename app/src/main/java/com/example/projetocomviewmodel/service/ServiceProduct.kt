package com.example.projetocomviewmodel.service

import com.example.projetocomviewmodel.model.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceProduct {

    @GET("/products")
    fun getProducts(): Call<List<Products>>

    @GET("/products/{id}")
    fun getSingleProduct(@Path("id") productId: Int): Call<Products>
}
