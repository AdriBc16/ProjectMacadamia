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

class elegir : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_elegir)

        val siginButton = findViewById<Button>(R.id.registrer_main)
        siginButton.setOnClickListener(View.OnClickListener {view : View? ->
            val intent = Intent(this@elegir, activity_signin::class.java)
            startActivity(intent)
        })
        val loginButton = findViewById<Button>(R.id.login_main)
        loginButton.setOnClickListener(View.OnClickListener {view : View? ->
            val intent = Intent(this@elegir, activiy_login::class.java)
            startActivity(intent)
        })
    }
}