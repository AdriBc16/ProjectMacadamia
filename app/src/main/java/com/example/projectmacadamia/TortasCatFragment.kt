package com.example.projectmacadamia

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class TortasCatFragment : Fragment() {

    private val imagenesMap = mapOf(
        R.id.tortaChocolate to R.drawable.tortachocolate,
        R.id.TortacaramelCarrotCake to R.drawable.tortacaramelzanahoria,
        R.id.tortaChocoMousse to R.drawable.tortachocomousse,
        R.id.TortaLemonBerry to R.drawable.tortalemonberry,
        R.id.tortaBirthdayCake to R.drawable.tortabirthday,
        R.id.TortaRedVelvet to R.drawable.tortaredvelvet,
        R.id.tortaCookieDough to R.drawable.tortacookiedough,
        R.id.TortaOreo to R.drawable.tortaoreo,
        R.id.BanoffePie to R.drawable.tortabanoffee,
        R.id.ChocolatePie to R.drawable.tortachocolate
    )

    private fun mostrarPopup(textoBoton: String, imagenResId: Int) {
        val opciones = textoBoton.trim().split(" ")
        if (opciones.size != 2) {
            Toast.makeText(requireContext(), "Formato inválido", Toast.LENGTH_SHORT).show()
            return
        }

        val opcionesFormateadas = opciones.map { opcion ->
            val partes = opcion.split("/")
            if (partes.size == 2) {
                val porciones = partes[0]
                val precioTexto = partes[1].replace("bs", "", ignoreCase = true)
                val precio = precioTexto.toIntOrNull() ?: 0
                Pair("$porciones - ${precio} Bs", precio)
            } else {
                Pair("Opción inválida", 0)
            }
        }

        val nombres = opcionesFormateadas.map { it.first }
        val precios = opcionesFormateadas.map { it.second }

        AlertDialog.Builder(requireContext())
            .setTitle("Selecciona una opción")
            .setItems(nombres.toTypedArray()) { _, which ->
                Carrito.agregar(Productos(nombres[which], precios[which], imagenResId, 1))
                Toast.makeText(requireContext(), "${nombres[which]} agregada al carrito", Toast.LENGTH_SHORT).show()
            }
            .show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tortas_cat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagenesMap.forEach { (botonId, imagenResId) ->
            view.findViewById<Button>(botonId)?.setOnClickListener { boton ->
                val texto = (boton as Button).text.toString()
                mostrarPopup(texto, imagenResId)
            }
        }
    }
}
