package com.example.projectmacadamia

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.projectmacadamia.elegir

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var storeButton: Button
    private lateinit var cookieButton: Button
    private lateinit var cakeButton: Button
    private lateinit var chesseButton: Button

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialización de los botones por ID
        storeButton = view.findViewById(R.id.StoreButton)
        cookieButton = view.findViewById(R.id.CookieButton)
        cakeButton = view.findViewById(R.id.CakeButton)
        chesseButton = view.findViewById(R.id.ChesseButton)

        storeButton.setOnClickListener{
            findNavController().navigate(R.id.storeFragment)
        }
        // Ejemplo de uso: imprimir mensaje en consola al hacer clic
        cookieButton.setOnClickListener {
            findNavController().navigate(R.id.cookieFragment)
        }

        cakeButton.setOnClickListener {
            findNavController().navigate(R.id.tortasFragment)
        }

        chesseButton.setOnClickListener {
            findNavController().navigate(R.id.cheesecakeFragment)
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}