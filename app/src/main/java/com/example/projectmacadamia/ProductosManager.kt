package com.example.projectmacadamia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData

object ProductosManager {
    private val _productos = MutableLiveData<List<Productos>>()
    val productos: LiveData<List<Productos>> get() = _productos

    init {
        // Inicializa con la lista de productos (puedes cargarla desde una BD luego)
        _productos.value = listOf(
            Productos("Galleta Chocochips", 16, R.drawable.chocochip, cantidad = 1, activo = false),
            Productos("Galleta Duo", 16, R.drawable.duo, cantidad = 1, activo = false),
            Productos("Galleta Red Velvet", 16, R.drawable.rv, cantidad = 1, activo = false),
            Productos("Galleta Cookies and Cream ", 16, R.drawable.oreo, cantidad = 1, activo = false),
            Productos("Galleta Cinaroll", 16, R.drawable.cinaroll, cantidad = 1, activo = false),
            Productos("Galleta Fruity Pebbles", 16, R.drawable.frutyy, cantidad = 1, activo = false),
            Productos("Galleta Lemon Pie", 18, R.drawable.lemon, cantidad = 1, activo = false),
            Productos("Galleta Brookie", 18, R.drawable.brookie, cantidad = 1, activo = false),
            Productos("Galleta Zanahoria", 18, R.drawable.zana, cantidad = 1, activo = false),
            Productos("Galleta Dulce de leche", 18, R.drawable.ddl, cantidad = 1, activo = false),
            Productos("Galleta Nutella", 20, R.drawable.nutella, cantidad = 1, activo = false),
            Productos("Galleta Ferrero", 20, R.drawable.ferrero, cantidad = 1, activo = false)
        )
    }

    fun actualizarProducto(producto: Productos) {
        val listaActual = _productos.value?.toMutableList() ?: mutableListOf()
        val index = listaActual.indexOfFirst { it.nombre == producto.nombre }
        if (index != -1) {
            listaActual[index] = producto
            _productos.value = listaActual
        }
    }
}


