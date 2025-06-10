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
import com.example.projectmacadamia.activiy_login

class activity_signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        val botonSignin = findViewById<Button>(R.id.button_SignIn)
        botonSignin.setOnClickListener {
            val intent = Intent(this@activity_signin, PadreActivity::class.java)
            startActivity(intent)
        }
    }
}