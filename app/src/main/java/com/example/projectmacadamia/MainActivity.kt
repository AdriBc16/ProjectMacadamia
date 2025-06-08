package com.example.projectmacadamia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

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


    }
}