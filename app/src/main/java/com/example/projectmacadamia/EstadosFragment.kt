package com.example.projectmacadamia

import Productos
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EstadosFragment : Fragment() {

    private lateinit var recyclerEspera: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_estados, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerEspera = view.findViewById(R.id.recyclerEspera)

        val productosConfirmados = arguments?.getParcelableArrayList<Productos>("productosConfirmados")

        if (productosConfirmados != null) {
            val adapter = EstadosAdapter(productosConfirmados)
            recyclerEspera.layoutManager = LinearLayoutManager(requireContext())
            recyclerEspera.adapter = adapter
        }
    }
}
