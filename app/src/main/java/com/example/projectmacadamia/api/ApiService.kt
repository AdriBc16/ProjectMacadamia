package com.example.projectmacadamia.api

import com.example.projectmacadamia.modelo.LoginRequest
import com.example.projectmacadamia.modelo.LoginResponse
import com.example.projectmacadamia.modelo.RegisterResponse
import com.example.projectmacadamia.modelo.UserRequest
import com.example.projectmacadamia.modelo.UsernameCheckResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @Headers("Accept: application/json")
    @POST("register")
    fun registerUser(@Body user: UserRequest): Call<RegisterResponse>


    @Headers("Accept: application/json")
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("check-username/{username}")
    fun checkUsername(@Path("username") username: String): Call<UsernameCheckResponse>


}
