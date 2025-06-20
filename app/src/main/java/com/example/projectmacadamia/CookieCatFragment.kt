package com.example.projectmacadamia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class CookieCatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cookie_cat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.galletaChocoChips)?.setOnClickListener {
            Carrito.agregar(Productos("Galleta Choco Chips", 16, R.drawable.chocochip, 1))
            Toast.makeText(requireContext(), "Galleta Choco Chips agregada al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.galletaDuo)?.setOnClickListener {
            Carrito.agregar(Productos("Galleta Duo", 16, R.drawable.duo, 1))
            Toast.makeText(requireContext(), "Galleta Duo agregada al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.galletaRedvelvet)?.setOnClickListener {
            Carrito.agregar(Productos("Galleta Red Velvet", 16, R.drawable.rv, 1))
            Toast.makeText(requireContext(), "Galleta Red Velvet agregada al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.galletaOreo)?.setOnClickListener {
            Carrito.agregar(Productos("Galleta Oreo", 16, R.drawable.oreo, 1))
            Toast.makeText(requireContext(), "Galleta Oreo agregada al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.galletaLemon)?.setOnClickListener {
            Carrito.agregar(Productos("Galleta Lemon", 18, R.drawable.lemon, 1))
            Toast.makeText(requireContext(), "Galleta Lemon agregada al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.galletaBrookie)?.setOnClickListener {
            Carrito.agregar(Productos("Galleta Brookie", 18, R.drawable.brookie, 1))
            Toast.makeText(requireContext(), "Galleta Brookie agregada al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.galletaZanahoria)?.setOnClickListener {
            Carrito.agregar(Productos("Galleta Zanahoria", 18, R.drawable.zana, 1))
            Toast.makeText(requireContext(), "Galleta Zanahoria agregada al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.galletaDdl)?.setOnClickListener {
            Carrito.agregar(Productos("Galleta Dulce de Leche", 18, R.drawable.ddl, 1))
            Toast.makeText(requireContext(), "Galleta Dulce de Leche agregada al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.galletaNutella)?.setOnClickListener {
            Carrito.agregar(Productos("Galleta Nutella", 20, R.drawable.nutella, 1))
            Toast.makeText(requireContext(), "Galleta Nutella agregada al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.galletaFerrero)?.setOnClickListener {
            Carrito.agregar(Productos("Galleta Ferrero", 20, R.drawable.ferrero, 1))
            Toast.makeText(requireContext(), "Galleta Ferrero agregada al carrito", Toast.LENGTH_SHORT).show()
        }
    }
}
