package com.example.projectmacadamia

import  android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class cheesecakeCat : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cheesecake_cat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.chocolateTrufado)?.setOnClickListener {
            Carrito.agregar(Productos("Cheesecake Chocolate Trufado", 310, R.drawable.cheese_chocolate_t, 1))
            Toast.makeText(requireContext(), "Chocolate Trufado agregado al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.frutosRojos)?.setOnClickListener {
            Carrito.agregar(Productos("Cheesecake Frutos Rojos", 350, R.drawable.cheese_frutos_r, 1))
            Toast.makeText(requireContext(), "Frutos Rojos agregado al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.manjar)?.setOnClickListener {
            Carrito.agregar(Productos("Cheesecake Manjar", 310, R.drawable.cheese_manjar, 1))
            Toast.makeText(requireContext(), "Manjar agregado al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.carrotCake)?.setOnClickListener {
            Carrito.agregar(Productos("Cheesecake Carrot Cake", 310, R.drawable.cheese_carrot, 1))
            Toast.makeText(requireContext(), "Carrot Cake agregado al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.cookiedough)?.setOnClickListener {
            Carrito.agregar(Productos("Cheesecake Cookie Dough", 310, R.drawable.cheese_cookie_d, 1))
            Toast.makeText(requireContext(), "Cookie Dough agregado al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.cheesecakenutella)?.setOnClickListener {
            Carrito.agregar(Productos("Cheesecake Nutella", 350,R.drawable.cheese_nutella, 1))
            Toast.makeText(requireContext(), "Nutella agregado al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.lemonPie)?.setOnClickListener {
            Carrito.agregar(Productos("Cheesecake Lemon Pie", 310,R.drawable.cheese_lemon_p, 1))
            Toast.makeText(requireContext(), "Lemon Pie agregado al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.lemonBerry)?.setOnClickListener {
            Carrito.agregar(Productos("Cheesecake Lemon Berry", 350,R.drawable.cheese_lemon_b, 1))
            Toast.makeText(requireContext(), "Lemon Berry agregado al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.chessecakeChocolate)?.setOnClickListener {
            Carrito.agregar(Productos("Cheesecake Chocolate", 350,R.drawable.cheese_chocolate, 1))
            Toast.makeText(requireContext(), "Chocolate agregado al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.oreo)?.setOnClickListener {
            Carrito.agregar(Productos("Cheesecake Oreo", 350,R.drawable.cheese_oreo, 1))
            Toast.makeText(requireContext(), "Oreo agregado al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.appleCrumble)?.setOnClickListener {
            Carrito.agregar(Productos("Cheesecake Apple Crumble", 310,R.drawable.cheese_apple_c, 1))
            Toast.makeText(requireContext(), "Apple Crumble agregado al carrito", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.bombom)?.setOnClickListener {
            Carrito.agregar(Productos("Cheesecake Bombom", 380,R.drawable.cheese_bombon, 1))
            Toast.makeText(requireContext(), "Bombom agregado al carrito", Toast.LENGTH_SHORT).show()
        }
    }
}
