package com.example.projectmacadamia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView

class ProduccionAdapter(
    private val productos: List<Productos>,
    private val onSwitchChanged: (Productos, Boolean) -> Unit
) : RecyclerView.Adapter<ProduccionAdapter.ProduccionViewHolder>() {

    inner class ProduccionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
        val nombreProducto: TextView = itemView.findViewById(R.id.nombreProducto)
        val precioProducto: TextView = itemView.findViewById(R.id.precioProducto)
        val switchActivo: SwitchCompat = itemView.findViewById(R.id.switchActivo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProduccionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_admin, parent, false)
        return ProduccionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProduccionViewHolder, position: Int) {
        val producto = productos[position]

        holder.imgProducto.setImageResource(producto.imagenResId)
        holder.nombreProducto.text = producto.nombre
        holder.precioProducto.text = "${producto.precio} Bs"
        holder.switchActivo.isChecked = producto.activo

        holder.switchActivo.setOnCheckedChangeListener(null)
        holder.switchActivo.setOnCheckedChangeListener { _, isChecked ->
            onSwitchChanged(producto, isChecked)
        }
    }

    override fun getItemCount() = productos.size
}
