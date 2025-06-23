package com.example.projectmacadamia

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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

        val layout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val dialog = AlertDialog.Builder(requireContext()).create()

        val editText = EditText(requireContext()).apply {
            hint = "sin nueces\ncon relleno"
            setLines(3)
        }

        val textoDescripcion = TextView(requireContext()).apply {
            text = "Descripción"
            setPadding(0, 30, 0, 10)
            textSize = 16f
        }

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, 0, 0, 20)
        }

        val btn15p = Button(requireContext()).apply {
            text = opcionesFormateadas[0].first
            setBackgroundResource(R.drawable.rounded_corners)
            setTextColor(Color.WHITE)
            layoutParams = params
            setOnClickListener {
                val desc = editText.text.toString()
                Carrito.agregar(
                    Productos(
                        nombre = opcionesFormateadas[0].first,
                        precio = opcionesFormateadas[0].second,
                        imagenResId = imagenResId,
                        descripcion = desc
                    )
                )
                Toast.makeText(requireContext(), "Agregado al carrito", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }

        val btn25p = Button(requireContext()).apply {
            text = opcionesFormateadas[1].first
            setBackgroundResource(R.drawable.rounded_corners)
            setTextColor(Color.WHITE)
            layoutParams = params
            setOnClickListener {
                val desc = editText.text.toString()
                Carrito.agregar(
                    Productos(
                        nombre = opcionesFormateadas[1].first,
                        precio = opcionesFormateadas[1].second,
                        imagenResId = imagenResId,
                        descripcion = desc
                    )
                )
                Toast.makeText(requireContext(), "Agregado al carrito", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }

        layout.addView(btn15p)
        layout.addView(btn25p)
        layout.addView(textoDescripcion)
        layout.addView(editText)

        dialog.setTitle("Selecciona una opción")
        dialog.setView(layout)
        dialog.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tortas_cat, container, false)

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
