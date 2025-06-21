package com.example.projectmacadamia

import retrofit2.Call
import retrofit2.http.GET
import com.google.gson.JsonObject

interface ApiService {
    @GET("productos")
    fun getProductos(): Call<JsonObject>
}