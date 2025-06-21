package com.example.projectmacadamia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TiendaFragment : Fragment() {
    private lateinit var adapter: TiendaAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tienda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerTienda)

        // Inicializa el adaptador con una lista vacía (se actualizará con el LiveData)
        adapter = TiendaAdapter(emptyList())
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        // Observa cambios en la lista de productos del Singleton
        ProductosManager.productos.observe(viewLifecycleOwner) { listaProductos ->
            // Filtra solo los productos activos
            val productosActivos = listaProductos.filter { it.activo }
            adapter.actualizarLista(productosActivos)
        }

    }
}
