package com.example.projectmacadamia

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectmacadamia.MainActivity

class activiy_login : AppCompatActivity() {

    private lateinit var userField: EditText
    private lateinit var passwordField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val botonLogIn = findViewById<Button>(R.id.botonLogin)
        val botonSignin = findViewById<TextView>(R.id.button_signin)


        userField = findViewById(R.id.user)
        passwordField = findViewById(R.id.password)
        botonSignin.setOnClickListener {
            val intent = Intent(this@activiy_login, activity_signin::class.java)
            startActivity(intent)
        }

        setupFieldValidations()

        botonLogIn.setOnClickListener {
            if (validateAllFields()) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@activiy_login, PadreActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setupFieldValidations() {
        // Validación para usuario
        userField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateUsername()
                userField.nextFocusDownId = if (validateUsername()) R.id.phone_number else View.NO_ID
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Validación para contraseña
        passwordField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validatePassword()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
    private fun validateUsername(): Boolean {
        val username = userField.text.toString().trim()
        if (username.isEmpty()) {
            userField.error = "Usuario requerido"
            userField.backgroundTintList = ColorStateList.valueOf(Color.RED)
            return false
        }
        /*if (existingUsers.contains(username)) {
            userField.error = "Usuario ya existe"
            userField.backgroundTintList = ColorStateList.valueOf(Color.RED)
            return false
        }*/
        userField.error = null
        userField.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
        return true
    }

    private fun validatePassword(): Boolean {
        val password = passwordField.text.toString().trim()
        return when {
            password.isEmpty() -> {
                showPasswordError("Contraseña requerida")
                false
            }
            else -> {
                showPasswordValid()
                true
            }
        }
    }

    private fun showPasswordError(message: String) {
        passwordField.error = message
        passwordField.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red))
    }

    private fun showPasswordValid() {
        passwordField.error = null
        passwordField.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.success_green))
    }

    private fun validateAllFields(): Boolean {
        val usernameValid = validateUsername()
        val passwordValid = validatePassword()
        return usernameValid && passwordValid
    }

}