package com.example.projectmacadamia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectmacadamia.MainActivity

class activiy_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val orderNowButton = findViewById<Button>(R.id.botonLogin)
        orderNowButton.setOnClickListener(View.OnClickListener {view : View? ->
            val intent = Intent(this@activiy_login, activity_profile::class.java)
            startActivity(intent)
        })
    }
}