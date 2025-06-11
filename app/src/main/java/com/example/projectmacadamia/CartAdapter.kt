package com.example.projectmacadamia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmacadamia.R
import com.example.projectmacadamia.Producto

class CartAdapter(
    private val productos: MutableList<Producto>,
    private val onTotalChanged: (Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.nombreProducto)
        val precio: TextView = itemView.findViewById(R.id.precioProducto)
        val imagen: ImageView = itemView.findViewById(R.id.imgProducto)
        val cantidad: TextView = itemView.findViewById(R.id.txtCantidad)
        val btnAdd: Button = itemView.findViewById(R.id.btnAdd)
        val btnSubtract: Button = itemView.findViewById(R.id.btnSubtract)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val producto = productos[position]

        holder.nombre.text = producto.nombre
        val total = producto.precio * producto.cantidad
        holder.precio.text = holder.itemView.context.getString(R.string.precio_total, total)
        holder.imagen.setImageResource(producto.imagenResId)
        holder.cantidad.text = producto.cantidad.toString()

        holder.btnAdd.setOnClickListener {
            producto.cantidad++
            notifyItemChanged(position)
            actualizarTotal()
        }

        holder.btnSubtract.setOnClickListener {
            if (producto.cantidad > 1) {
                producto.cantidad--
                notifyItemChanged(position)
                actualizarTotal()
            } else {
                productos.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, productos.size)
                actualizarTotal()
            }
        }
    }

    override fun getItemCount(): Int = productos.size

    fun actualizarTotal() {
        val total = productos.sumOf { it.precio * it.cantidad }
        onTotalChanged(total)
    }
}
