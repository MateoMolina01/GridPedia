package com.maestre.gridpedia.view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maestre.gridpedia.databinding.ActivityLoginBinding
import com.maestre.gridpedia.model.persistencia.AdminSQLiteConexion

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var dbHelper: AdminSQLiteConexion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = AdminSQLiteConexion(this)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener lista de usuarios desde la base de datos
        val usuarios = dbHelper.obtenerUsuarios(contexto = this);
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, usuarios)
        binding.autoCompleteUserName.setAdapter(adapter)

        // Validación del botón
        binding.btnValidate.setOnClickListener {
            val username = binding.autoCompleteUserName.text.toString()
            val password = binding.password.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (dbHelper.comprobarUsuario(contexto = this, username, password)) {
                // Usuario y contraseña correctos
                val intent = Intent(this, MainActivity::class.java)
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                intent.putExtra("USERNAME", username)
                startActivity(intent)
                finish()
            } else {
                // Usuario no encontrado, preguntar si quiere registrarse
                mostrarDialogoRegistro(username, password)
            }
        }
    }

    private fun mostrarDialogoRegistro(username: String, password: String) {
        AlertDialog.Builder(this)
            .setTitle("Usuario no encontrado")
            .setMessage("¿Quieres registrarte como nuevo usuario?")
            .setPositiveButton("Sí") { dialogInterface: DialogInterface, _: Int ->
                if (dbHelper.registrarUsuario(contexto = this, username, password) != (-1).toLong()) {
                    Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            .show()
    }
}
