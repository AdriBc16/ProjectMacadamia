package com.example.projectmacadamia.modelo

data class UserRequest(
    val username: String,
    val name: String,
    val lastnames: String,
    val password: String,
    val email: String,
    val phone: String,
    val address: String,
    val category: String
)