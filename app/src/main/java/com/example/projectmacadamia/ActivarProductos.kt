package com.example.projectmacadamia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActivarProductos : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflamos el nuevo layout que contiene solo el RecyclerView
        return inflater.inflate(R.layout.fragment_activar_productos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerActivarProductos)

        // 1. Obtén los productos del Singleton (en lugar de la lista local)
        val productos = ProductosManager.productos.value ?: emptyList()

        // 2. Adaptador con callback para actualizar el Singleton
        val adapter = ProduccionAdapter(productos) { producto, activo ->
            producto.activo = activo
            ProductosManager.actualizarProducto(producto) // ✅ Actualiza el Singleton
            Toast.makeText(
                requireContext(),
                "${producto.nombre} ${if (activo) "activado" else "desactivado"}",
                Toast.LENGTH_SHORT
            ).show()
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }
}
