package com.maestre.gridpedia.view

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maestre.gridpedia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Datos para AutoCompleteTextView
        val usuarios = listOf("admin", "user1", "user2")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, usuarios)
        binding.autoCompleteUserName.setAdapter(adapter)

        // Validación del botón
        binding.btnValidate.setOnClickListener {
            val username = binding.autoCompleteUserName.text.toString()
            val password = binding.password.text.toString()

            //validamos contraseña mostramos un toast y vamos a la siguiente pantalla si la contra esta bien
            if (validarContraseña(password)) {
                val intent = Intent(this, MainActivity::class.java)
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                intent.putExtra("USERNAME", if (username.isEmpty()) "Usuario" else username)
                startActivity(intent)
                finish() // Para que no puedan volver a la pantalla de login con el botón atrás
            } else {
                binding.password.error = "Contraseña incorrecta"
            }
        }

    }

    private fun validarContraseña(contra: String): Boolean {

        var correcta = false

        if (contra != "admin"){
            binding.passwordLayout.setError("Contraseña Incorrecta")
        } else {
            binding.passwordLayout.setError(null)
            correcta = true
        }
        return correcta
    }
}
