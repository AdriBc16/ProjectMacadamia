package com.example.projectmacadamia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartFragment : Fragment() {

    private lateinit var recyclerCart: RecyclerView
    private lateinit var btnConfirmCart: Button
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerCart = view.findViewById(R.id.recyclerCart)
        btnConfirmCart = view.findViewById(R.id.btnConfirmCart)

        val productos = Carrito.obtenerProductos()

        adapter = CartAdapter(productos) { total ->
            val totalTexto = getString(R.string.precio_total, total)
            btnConfirmCart.text = getString(R.string.confirmar_con_precio, totalTexto)
        }

        recyclerCart.layoutManager = LinearLayoutManager(requireContext())
        recyclerCart.adapter = adapter

        adapter.actualizarTotal()
    }
}
