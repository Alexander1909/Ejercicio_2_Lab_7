package com.example.ejercicio_2_lab_7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio_2_lab_7.data.Articulo

class ArticuloAdapter : ListAdapter<Articulo, ArticuloAdapter.ArticuloViewHolder>(ArticuloDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticuloViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return ArticuloViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticuloViewHolder, position: Int) {
        val articulo = getItem(position)
        holder.bind(articulo)
    }

    class ArticuloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNombre: TextView = itemView.findViewById(R.id.tvName)
        private val tvDescripcion: TextView = itemView.findViewById(R.id.tvEmail)

        fun bind(articulo: Articulo) {
            tvNombre.text = articulo.nombre
            tvDescripcion.text = articulo.descripcion
        }
    }

    class ArticuloDiffCallback : DiffUtil.ItemCallback<Articulo>() {
        override fun areItemsTheSame(oldItem: Articulo, newItem: Articulo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Articulo, newItem: Articulo): Boolean {
            return oldItem == newItem
        }
    }
}
