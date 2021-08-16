package com.example.projetocomviewmodel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projetocomviewmodel.R
import com.example.projetocomviewmodel.model.Products

class Adapter(val onClick: (Products) -> Unit) : RecyclerView.Adapter<ProductViewHolder>() {

    private var listOfProducts = mutableListOf<Products>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_produtcs, parent, false).apply {
            return ProductViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        listOfProducts[position].apply {
        holder.bind(this)
            holder.itemView.setOnClickListener{onClick(this)}
        }
    }

    override fun getItemCount(): Int = listOfProducts.size

    fun update(newList: List<Products>) {
        listOfProducts = mutableListOf()
        listOfProducts.addAll(newList)
        notifyDataSetChanged()
    }
}

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val productImageView: ImageView = itemView.findViewById(R.id.productImageView)
    private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    private val subtitleTextView: TextView = itemView.findViewById(R.id.subtitleTextView)
    private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)

    fun bind(product: Products) {
        titleTextView.text = product.title
        subtitleTextView.text = product.description

        priceTextView.text = "R$ ${product.price}"

        Glide.with(itemView.context)
            .load(product.image)
            .into(productImageView)

    }


}