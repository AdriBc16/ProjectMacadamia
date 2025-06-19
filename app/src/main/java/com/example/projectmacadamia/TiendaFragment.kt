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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tienda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerTienda)

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
            Productos("Galleta Nutella", 18, R.drawable.nutella, cantidad = 1, activo = true),
            Productos("Galleta Ferrero", 18, R.drawable.ferrero, cantidad = 1, activo = true)
        )

        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = TiendaAdapter(productos)

    }
}
