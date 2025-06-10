package com.example.projectmacadamia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
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
        val botonLogIn = findViewById<Button>(R.id.botonLogin)
        val botonSignin = findViewById<TextView>(R.id.button_signin)

        botonSignin.setOnClickListener {
            val intent = Intent(this@activiy_login, activity_signin::class.java)
            startActivity(intent)
        }

        botonLogIn.setOnClickListener {
            val intent = Intent(this@activiy_login, PadreActivity::class.java)
            startActivity(intent)
        }
    }
}