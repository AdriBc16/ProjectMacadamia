package com.example.projectmacadamia

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectmacadamia.MainActivity
import com.example.projectmacadamia.activiy_login

class activity_signin : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private var isSpinnerSelected = false

    private lateinit var userField: EditText
    private lateinit var phoneField: EditText
    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var adressField: EditText
    private lateinit var signInButton: Button
    private lateinit var logInButton: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // Inicialización de vistas
        userField = findViewById(R.id.user)
        phoneField = findViewById(R.id.phone_number)
        emailField = findViewById(R.id.email)
        passwordField = findViewById(R.id.password)
        adressField = findViewById(R.id.adress)
        signInButton = findViewById(R.id.button_SignIn)
        logInButton = findViewById(R.id.button_login)

        logInButton.setOnClickListener {
            val intent = Intent(this, activiy_login::class.java)
            startActivity(intent)
        }
        // Configuración de campos
        passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        spinner = findViewById<Spinner>(R.id.spinner)
        setupSpinner()

        // Agregar validación en tiempo real
        setupFieldValidations()

        signInButton.setOnClickListener {
            if (validateAllFields()) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PadreActivity::class.java)
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

        // Validación para teléfono
        phoneField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validatePhone()
                phoneField.nextFocusDownId = if (validatePhone()) R.id.email else View.NO_ID
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Validación para email
        emailField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateEmail()
                emailField.nextFocusDownId = if (validateEmail()) R.id.password else View.NO_ID
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
        // Validación para adress
        adressField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateAdress()
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

    private fun validatePhone(): Boolean {
        val phone = phoneField.text.toString().trim()

        when {
            phone.isEmpty() -> {
                phoneField.error = "Teléfono requerido"
                phoneField.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red))
                return false
            }
            phone.length != 8 -> {
                phoneField.error = "Debe tener exactamente 8 dígitos"
                phoneField.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red))
                return false
            }
            !phone.matches(Regex("^\\d+$")) -> {
                phoneField.error = "Solo se permiten números"
                phoneField.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.red))
                return false
            }
            else -> {
                phoneField.error = null
                phoneField.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.success_green))
                return true
            }
        }
    }

    private fun validateEmail(): Boolean {
        val email = emailField.text.toString().trim()
        if (email.isEmpty()) {
            emailField.error = "Email requerido"
            emailField.backgroundTintList = ColorStateList.valueOf(Color.RED)
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.error = "Formato inválido"
            emailField.backgroundTintList = ColorStateList.valueOf(Color.RED)
            return false
        }
        emailField.error = null
        emailField.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
        return true
    }

//    private fun validatePassword(): Boolean {
//        val password = passwordField.text.toString().trim()
//        if (password.isEmpty()) {
//            passwordField.error = "Contraseña requerida"
//            passwordField.backgroundTintList = ColorStateList.valueOf(Color.RED)
//            return false
//        }
//        if (password.length < 8) {
//            passwordField.error = "Mínimo 8 caracteres"
//            passwordField.backgroundTintList = ColorStateList.valueOf(Color.RED)
//            return false
//        }
//        passwordField.error = null
//        passwordField.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
//        return true
//    }

    private fun validatePassword(): Boolean {
        val password = passwordField.text.toString().trim()
        val passwordPattern = "^(?=.*[A-Z])(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}\$"

        return when {
            password.isEmpty() -> {
                showPasswordError("Contraseña requerida")
                false
            }
            !password.matches(passwordPattern.toRegex()) -> {
                showPasswordError("Debe tener: 8+ caracteres, 1 mayúscula y 1 carácter especial")
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

    private fun validateAdress(): Boolean {
        val adress = adressField.text.toString().trim()
        if (adress.isEmpty()) {
            adressField.error = "Direccion requerida"
            adressField.backgroundTintList = ColorStateList.valueOf(Color.RED)
            return false
        }
        return true
    }
    private fun setupSpinner() {
        // Crear un ArrayAdapter usando el array de categorías
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.categorias,
            android.R.layout.simple_spinner_item
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        spinner.adapter = adapter

        // Listener para validar la selección
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) {  // Si no es la primera opción (el prompt)
                    isSpinnerSelected = true
                    (view as? TextView)?.setTextColor(ContextCompat.getColor(this@activity_signin, R.color.white))
                } else {
                    isSpinnerSelected = false
                    (view as? TextView)?.setTextColor(ContextCompat.getColor(this@activity_signin, R.color.white))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                isSpinnerSelected = false
            }
        }
    }
    private fun validateSpinner(): Boolean {
        return if (spinner.selectedItemPosition == 0) {  // Si está seleccionado el prompt
            // Mostrar error
            (spinner.selectedView as? TextView)?.apply {
                error = "Debe seleccionar una categoría"
                setTextColor(ContextCompat.getColor(this@activity_signin, R.color.white))
            }
            false
        } else {
            // Validación correcta
            (spinner.selectedView as? TextView)?.apply {
                error = null
                setTextColor(ContextCompat.getColor(this@activity_signin, R.color.white))
            }
            true
        }
    }

    private fun validateAllFields(): Boolean {
        val usernameValid = validateUsername()
        val phoneValid = validatePhone()
        val emailValid = validateEmail()
        val passwordValid = validatePassword()
        val adressValid = validateAdress()
        val spinnerValid = validateSpinner()
        return usernameValid && phoneValid && emailValid && passwordValid && adressValid && spinnerValid
    }
}