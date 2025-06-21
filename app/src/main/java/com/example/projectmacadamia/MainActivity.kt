package com.example.projectmacadamia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.projectmacadamia.api.ApiService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val orderNowButton = findViewById<Button>(R.id.bottonOrderNow)
        orderNowButton.setOnClickListener(View.OnClickListener {view : View? ->
            val intent = Intent(this@MainActivity, elegir::class.java)
            startActivity(intent)
        })

        // Configurar Retrofit
//        val retrofit = Retrofit.Builder()
////            .baseUrl("http://10.0.2.2:8000/api/") // Para emulador
//            .baseUrl("http://192.168.x.x:8000/api/") // Para dispositivo físico (usar tu IP local)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        // Crear instancia del servicio
//        val apiService = retrofit.create(ApiService::class.java)

        // Hacer la llamada
//        apiService.getProductos().enqueue(object : Callback<JsonObject> {
//            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
//                if (response.isSuccessful) {
//                    val productos = response.body()
//                    Log.d("API_SUCCESS", productos.toString())
//                    // Aquí puedes procesar los datos
//                } else {
//                    Log.e("API_ERROR", "Código: ${response.code()}")
//                }
//            }
//
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                Log.e("API_FAILURE", "Error: ${t.message}")
//            }
//        })
    }
}