package com.example.projectmacadamia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TiendaAdapter(
    private var productos: List<Productos>
) : RecyclerView.Adapter<TiendaAdapter.TiendaViewHolder>() {

    fun actualizarLista(nuevaLista: List<Productos>) {
        this.productos = nuevaLista
        notifyDataSetChanged() // Notifica al RecyclerView que los datos cambiaron
    }

    inner class TiendaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtDescripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        val txtPrecio: TextView = itemView.findViewById(R.id.txtPrecio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiendaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cliente, parent, false)
        return TiendaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TiendaViewHolder, position: Int) {
        val producto = productos[position]
        holder.imgProducto.setImageResource(producto.imagenResId)
        holder.txtNombre.text = producto.nombre
        holder.txtDescripcion.text = "Producto artesanal y delicioso"
        holder.txtPrecio.text = "${producto.precio} Bs"

        holder.itemView.setOnClickListener {
            Carrito.agregar(producto)
            Toast.makeText(
                holder.itemView.context,
                "${producto.nombre} añadido al carrito",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = productos.size
}
