package com.example.projectmacadamia

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.projectmacadamia.api.RetrofitClient
import com.example.projectmacadamia.modelo.RegisterResponse
import com.example.projectmacadamia.modelo.UserRequest
import com.example.projectmacadamia.modelo.UsernameCheckResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class activity_signin : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private var isSpinnerSelected = false

    private lateinit var userField: EditText
    private lateinit var nameField: EditText
    private lateinit var lastnamesField: EditText
    private lateinit var phoneField: EditText
    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var addressField: EditText
    private lateinit var signInButton: Button
    private lateinit var logInButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // Inicialización de vistas
        userField = findViewById(R.id.user)
        nameField = findViewById(R.id.nombre)
        lastnamesField = findViewById(R.id.apellido)
        phoneField = findViewById(R.id.phone_number)
        emailField = findViewById(R.id.email)
        passwordField = findViewById(R.id.password)
        addressField = findViewById(R.id.address)
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
        setupPasswordToggle(passwordField)


        // Agregar validación en tiempo real
        setupFieldValidations()

        signInButton.setOnClickListener {
            validateUsername { isValid ->
                if (!isValid) return@validateUsername
            }
            if (!validateAllFields() || !validateSpinner()) {
                Toast.makeText(this, "Complete todos los campos correctamente", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userRequest = UserRequest(
                username = userField.text.toString(),
                name = nameField.text.toString(),
                lastnames = lastnamesField.text.toString(),
                email = emailField.text.toString(),
                password = passwordField.text.toString(),
                phone = phoneField.text.toString(),
                address = addressField.text.toString(),
                category = spinner.selectedItem.toString()
            )

            RetrofitClient.api.registerUser(userRequest).enqueue(object : Callback<RegisterResponse> {

                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    try {
                        if (response.isSuccessful && response.body() != null) {
                            val user = response.body()!!.usuario

                            // Guardar en SharedPreferences
                            val prefs = getSharedPreferences("auth", MODE_PRIVATE)
                            prefs.edit().apply {
                                putString("username", user.username)
                                putString("name", user.name)
                                putString("lastnames", user.lastnames)
                                putString("email", user.email)
                                putString("phone", user.phone)
                                putString("address", user.address)
                                putString("category", user.category)
                                apply()
                            }

                            val category = spinner.selectedItem.toString().trim().lowercase()

                            Toast.makeText(this@activity_signin, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                            try {
                                when (category) {
                                    "produccion" -> startActivity(Intent(this@activity_signin, Padre2Activity::class.java))
                                    "cliente" -> startActivity(Intent(this@activity_signin, PadreActivity::class.java))
                                    else -> Toast.makeText(this@activity_signin, "Categoría no reconocida: $category", Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: Exception) {
                                Log.e("RedireccionError", "Error al cambiar de pantalla: ${e.message}")
                                Toast.makeText(this@activity_signin, "Error al cambiar de pantalla: ${e.message}", Toast.LENGTH_LONG).show()
                            }
                            finish()

                    } else {
                        val errorBody = response.errorBody()?.string()

                        try {
                            val jsonObject = JSONObject(errorBody ?: "")
                            val errors = jsonObject.optJSONObject("errors")

                            if (errors != null) {
                                val usernameErrors = errors.optJSONArray("username")
                                if (usernameErrors != null && usernameErrors.length() > 0) {
                                    val usernameErrorMsg = usernameErrors.getString(0)
                                    userField.error = usernameErrorMsg
                                    userField.backgroundTintList = ColorStateList.valueOf(Color.RED)
                                }

                                val emailErrors = errors.optJSONArray("email")
                                if (emailErrors != null && emailErrors.length() > 0) {
                                    val emailErrorMsg = emailErrors.getString(0)
                                    emailField.error = emailErrorMsg
                                    emailField.backgroundTintList = ColorStateList.valueOf(Color.RED)
                                }
                            }

                            Toast.makeText(this@activity_signin, jsonObject.optString("message", "Error al registrar"), Toast.LENGTH_LONG).show()

                            } catch (e: Exception) {
                                Log.e("RegistroError", "No se pudo parsear error JSON: ${e.message}")
                                Toast.makeText(this@activity_signin, "Error inesperado", Toast.LENGTH_LONG).show()
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("RegistroCatch", "Excepción al procesar respuesta: ${e.message}")
                        Toast.makeText(this@activity_signin, "Error inesperado: ${e.message}", Toast.LENGTH_LONG).show()

                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.e("RegistroFallo", "Fallo de red: ${t.message}")
                    Toast.makeText(
                        this@activity_signin,
                        "Fallo de conexión: ${t.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
        }

    }

    private fun setupFieldValidations() {
        // Validación para usuario
//        userField.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                validateUsername()
//                userField.nextFocusDownId = if (validateUsername()) R.id.nombre else View.NO_ID
//            }
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//        })
        nameField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateName()
                nameField.nextFocusDownId = if (validateName()) R.id.apellido else View.NO_ID
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        lastnamesField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateLastName()
                lastnamesField.nextFocusDownId = if (validateLastName()) R.id.phone_number else View.NO_ID
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
                passwordField.nextFocusDownId = if (validatePassword()) R.id.address else View.NO_ID
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        // Validación para adress
        addressField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateAdress()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun validateUsername(callback: (Boolean) -> Unit) {
        val username = userField.text.toString().trim()
        if (username.isEmpty()) {
            userField.error = "Usuario requerido"
            userField.backgroundTintList = ColorStateList.valueOf(Color.RED)
            callback(false)
            return
        }

        // Llamada a la API para validar existencia del nombre de usuario
        RetrofitClient.api.checkUsername(username).enqueue(object : Callback<UsernameCheckResponse> {
            override fun onResponse(call: Call<UsernameCheckResponse>, response: Response<UsernameCheckResponse>) {
                if (response.isSuccessful && response.body()?.exists == true) {
                    userField.error = "Usuario ya existe"
                    userField.backgroundTintList = ColorStateList.valueOf(Color.RED)
                    callback(false)
                } else {
                    userField.error = null
                    userField.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                    callback(true)
                }
            }

            override fun onFailure(call: Call<UsernameCheckResponse>, t: Throwable) {
                userField.error = "Error al verificar usuario"
                userField.backgroundTintList = ColorStateList.valueOf(Color.RED)
                callback(false)
            }
        })
    }
    private fun validateName(): Boolean {
        val name = nameField.text.toString().trim()
        if (name.isEmpty()) {
            nameField.error = "Nombre requerido"
            nameField.backgroundTintList = ColorStateList.valueOf(Color.RED)
            return false
        }
        nameField.error = null
        return true
    }
    private fun validateLastName(): Boolean {
        val lastName = lastnamesField.text.toString().trim()
        if (lastName.isEmpty()) {
            lastnamesField.error = "Apellido requerido"
            lastnamesField.backgroundTintList = ColorStateList.valueOf(Color.RED)
            return false
        }
        lastnamesField.error = null
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
    private var isPasswordVisible = false
    private fun setupPasswordToggle(passwordField: EditText) {
        passwordField.setOnTouchListener { v, event ->
            val drawableEnd = 2 // Right drawable
            if (event.action == MotionEvent.ACTION_UP) {
                val drawable = passwordField.compoundDrawables[drawableEnd]
                if (drawable != null && event.rawX >= (passwordField.right - drawable.bounds.width())) {
                    isPasswordVisible = !isPasswordVisible
                    if (isPasswordVisible) {
                        passwordField.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                        passwordField.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye, 0)
                    } else {
                        passwordField.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        passwordField.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye, 0)
                    }
                    // Mantener el cursor al final
                    passwordField.setSelection(passwordField.text.length)
                    return@setOnTouchListener true
                }
            }
            false
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
        val address = addressField.text.toString().trim()
        if (address.isEmpty()) {
            addressField.error = "Direccion requerida"
            addressField.backgroundTintList = ColorStateList.valueOf(Color.RED)
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
                    (view as? TextView)?.setTextColor(ContextCompat.getColor(this@activity_signin, R.color.black))
                } else {
                    isSpinnerSelected = false
                    (view as? TextView)?.setTextColor(ContextCompat.getColor(this@activity_signin, R.color.black))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                isSpinnerSelected = false
            }
        }
    }
    private fun validateSpinner(): Boolean {
        if (spinner.selectedItemPosition == 0) {
            (spinner.selectedView as? TextView)?.error = " "
            return false
        }
        return true
    }
    private fun validateAllFields(): Boolean {
//        val usernameValid = validateUsername()
        val nameValid = validateName()
        val lastValid = validateLastName()
        val phoneValid = validatePhone()
        val emailValid = validateEmail()
        val passwordValid = validatePassword()
        val adressValid = validateAdress()
        val spinnerValid = validateSpinner()
        return /*usernameValid &&*/ nameValid && lastValid && phoneValid && emailValid && passwordValid && adressValid && spinnerValid
    }
}