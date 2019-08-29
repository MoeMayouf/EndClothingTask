package com.test.endclothingtask.presentation.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.endclothingtask.R
import com.test.endclothingtask.common.inflate
import com.test.endclothingtask.data.model.Catalog
import com.test.endclothingtask.data.model.Products
import kotlinx.android.synthetic.main.row.view.*

class CatalogAdapter(private val catalog: Catalog) : RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        //return CatalogViewHolder(parent.inflate(R.layout.row))
        return CatalogViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false))
    }

    override fun getItemCount(): Int {
        return catalog.products.size
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        holder.bind(catalog.products[position])
    }

    class CatalogViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view = v

        fun bind(products: Products) {

            Picasso.get().load(products.image).into(view.imgCatalog)

            view.tvName.text = products.name
            view.tvPrice.text = products.price
        }

    }
}