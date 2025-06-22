package com.example.projectmacadamia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProduccionFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Cargamos el layout del fragmento
        return inflater.inflate(R.layout.fragment_produccion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerProductos = view.findViewById<RecyclerView>(R.id.recyclerActivarProductos)

        // Obtiene los productos del Singleton
        val listaProductos = ProductosManager.productos.value ?: emptyList()
        val adapter = ProduccionAdapter(listaProductos) { producto, activo ->
            producto.activo = activo
            ProductosManager.actualizarProducto(producto)  // Actualiza el Singleton
            Toast.makeText(context, "${producto.nombre} ${if (activo) "activado" else "desactivado"}", Toast.LENGTH_SHORT).show()
        }

        recyclerProductos.layoutManager = LinearLayoutManager(requireContext())
        recyclerProductos.adapter = adapter

    }
}
