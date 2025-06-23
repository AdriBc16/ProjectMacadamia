package com.example.projectmacadamia

import Productos
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EstadosAdapter(private val productos: List<Productos>) :
    RecyclerView.Adapter<EstadosAdapter.EstudioViewHolder>() {

    inner class EstudioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgProductoEstado)
        val nombre: TextView = view.findViewById(R.id.nombreProducto)
        val precio: TextView = view.findViewById(R.id.precioProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstudioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estados_productos, parent, false)
        return EstudioViewHolder(view)
    }

    override fun onBindViewHolder(holder: EstudioViewHolder, position: Int) {
        val producto = productos[position]
        holder.img.setImageResource(producto.imagenResId)
        holder.nombre.text = producto.nombre
        val total = producto.precio * producto.cantidad
        holder.precio.text = holder.itemView.context.getString(R.string.precio_total, total)
    }

    override fun getItemCount(): Int = productos.size
}
