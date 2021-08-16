package com.example.projetocomviewmodel.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetocomviewmodel.model.Products
import com.example.projetocomviewmodel.repository.ProductRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrimeiroViewModel : ViewModel(){

    private val _products =
        MutableLiveData<List<Products>>()//criada para manipulacao interna da variavel
    val products: LiveData<List<Products>> =
        _products//criada para manipulacao externa da variavel, espelhando o mutableLiveData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error


    private val repository = ProductRepository()//chamando a call da API (antigo by lazy)

    fun fetchProdutos() {
        repository.getProducts{ list, error ->
            list?.apply {
                _products.value = this
            }

            error?.apply {
                _error.value = this
            }

        }
    }
}