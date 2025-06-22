package com.example.projectmacadamia

data class Productos(
    val nombre: String,
    val precio: Int,
    val imagenResId: Int,
    var cantidad: Int = 1,
    var activo: Boolean = false,
    var descripcion: String = ""
)


