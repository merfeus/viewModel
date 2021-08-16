package com.example.projetocomviewmodel.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetocomviewmodel.R
import com.example.projetocomviewmodel.adapter.Adapter
import com.example.projetocomviewmodel.model.Products
import com.example.projetocomviewmodel.view_model.PrimeiroViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment(R.layout.main_fragment) {
    companion object {
        fun newInstance() = MainFragment()
    }
    private lateinit var viewModel: PrimeiroViewModel//chamando viewModel
    private lateinit var recyclerView: RecyclerView//chamando recyclerView
    private val adapter = Adapter {

    }//chamando adapter com a acao de click

    private val productsObserver = Observer<List<Products>> { newList ->
        adapter.update(newList)//observer criado para trazer o viewmodel
    }

    private val errorObserver = Observer<String> { error ->
        Snackbar.make(requireView(), error, Snackbar.LENGTH_LONG).show()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerVIew)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(PrimeiroViewModel::class.java)

        viewModel.products.observe(viewLifecycleOwner, productsObserver)

        viewModel.error.observe(viewLifecycleOwner, errorObserver)

        viewModel.fetchProdutos()
    }
}