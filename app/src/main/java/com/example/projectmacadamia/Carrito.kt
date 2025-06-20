package com.example.projectmacadamia

object Carrito {
    private val productos = mutableListOf<Productos>()

    fun agregar(producto: Productos) {
        val existente = productos.find { it.nombre == producto.nombre }
        if (existente != null) {
            existente.cantidad++
        } else {
            productos.add(producto.copy())
        }
    }

    fun obtenerProductos(): MutableList<Productos> {
        return productos
    }

    fun limpiar() {
        productos.clear()
    }

    fun total(): Int {
        return productos.sumOf { it.precio * it.cantidad }
    }
}
