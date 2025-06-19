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

        // Referencia al RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerActivarProductos)

        val productos = mutableListOf(
            Productos("Galleta Chocochips", 16, R.drawable.chocochip, cantidad = 1, activo = true),
            Productos("Galleta Duo", 16, R.drawable.duo, cantidad = 1, activo = false),
            Productos("Galleta Red Velvet", 16, R.drawable.rv, cantidad = 1, activo = true),
            Productos("Galleta Cookies and Cream ", 16, R.drawable.oreo, cantidad = 1, activo = true),
            Productos("Galleta Cinaroll", 16, R.drawable.cinaroll, cantidad = 1, activo = true),
            Productos("Galleta Fruity Pebbles", 16, R.drawable.frutyy, cantidad = 1, activo = true),
            Productos("Galleta Lemon Pie", 18, R.drawable.lemon, cantidad = 1, activo = true),
            Productos("Galleta Brookie", 18, R.drawable.brookie, cantidad = 1, activo = true),
            Productos("Galleta Zanahoria", 18, R.drawable.zana, cantidad = 1, activo = true),
            Productos("Galleta Dulce de leche", 18, R.drawable.ddl, cantidad = 1, activo = true),
            Productos("Galleta Nutella", 20, R.drawable.nutella, cantidad = 1, activo = true),
            Productos("Galleta Ferrero", 20, R.drawable.ferrero, cantidad = 1, activo = true)
        )

        // Adaptador de productos
        val adapter = ProduccionAdapter(productos) { producto, activo ->
            producto.activo = activo
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
