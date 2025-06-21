package com.example.projectmacadamia.modelo

data class Usuario(
    val id_usuario: Int,
    val username: String,
    val name: String,
    val lastnames: String,
    val email: String,
    val phone: String,
    val address: String,
    val category: String
)