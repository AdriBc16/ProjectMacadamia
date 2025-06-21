package com.example.projectmacadamia.api

import com.example.projectmacadamia.modelo.RegisterResponse
import com.example.projectmacadamia.modelo.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Accept: application/json")
    @POST("register")
    fun registerUser(@Body user: UserRequest): Call<RegisterResponse>
}
