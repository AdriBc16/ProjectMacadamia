package com.example.projectmacadamia

data class Producto(
    val nombre: String,
    val precio: Int,
    val imagenResId: Int,
    var cantidad: Int = 1
)
