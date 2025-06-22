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
import com.example.projectmacadamia.api.RetrofitClient
import com.example.projectmacadamia.modelo.LoginRequest
import com.example.projectmacadamia.modelo.LoginResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

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
            if (!validateAllFields()) {
                Toast.makeText(this, "Complete los campos correctamente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val username = userField.text.toString()
            val password = passwordField.text.toString()

            val loginRequest = LoginRequest(username, password)

            RetrofitClient.api.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful && response.body() != null) {
                        val loginResponse = response.body()!!
                        val token = loginResponse.token
                        val user = loginResponse.usuario

                        val prefs = getSharedPreferences("auth", MODE_PRIVATE)
                        prefs.edit().apply {
                            putString("token", token)
                            putString("username", user.username)
                            putString("name", user.name)
                            putString("lastnames", user.lastnames)
                            putString("email", user.email)
                            putString("phone", user.phone)
                            putString("address", user.address)
                            apply()
                        }

                        Toast.makeText(this@activiy_login, "Login exitoso", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@activiy_login, PadreActivity::class.java))
                        finish()
                    } else {
                        // Extraer el mensaje del cuerpo de error
                        val errorBody = response.errorBody()?.string()
                        val errorJson = JSONObject(errorBody ?: "{}")
                        val errorType = errorJson.optString("errorType")
                        val message = errorJson.optString("message", "Datos incorrectos")

                        when (errorType) {
                            "user_not_found" -> Toast.makeText(this@activiy_login, "Usuario incorrecto", Toast.LENGTH_SHORT).show()
                            "incorrect_password" -> Toast.makeText(this@activiy_login, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                            else -> Toast.makeText(this@activiy_login, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@activiy_login, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })

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