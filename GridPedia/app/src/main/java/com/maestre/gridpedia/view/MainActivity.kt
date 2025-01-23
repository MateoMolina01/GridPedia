package com.maestre.gridpedia.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.maestre.gridpedia.R
import com.maestre.gridpedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar : MaterialToolbar = binding.materialToolbar
        setSupportActionBar(toolbar)

        // Obtener el nombre del usuario del Intent
        val username = intent.getStringExtra("USERNAME") ?: "Usuario"

        // Mostrar el nombre en el TextView
        binding.tvUsername.text = "Hola, $username"

        // Acciones de los botones
        binding.btnDriversStandings.setOnClickListener {
            val intent = Intent(this, DriversStandingsActivity::class.java)
            startActivity(intent)
        }


        binding.btnTeamsStandings.setOnClickListener {
            val intent = Intent(this, TeamPagerActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
   }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mi_menu, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // Obtener el color seleccionado
            val selectedColor = data?.getStringExtra("SELECTED_COLOR")
            selectedColor?.let {
                binding.materialToolbar.setBackgroundColor(android.graphics.Color.parseColor(it))
            }
        }
    }

    // Manejar la selección de elementos del menú, de momento dejo dos toast
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_cambiar_color -> {

                val intent = Intent(this, ChangeColorActivity::class.java)

                //tengo que ponerlo como result porque si no se va a cerrar la actividad
                startActivityForResult(intent,1) // Abrir ChangeColorActivity
                true

            }
            R.id.menu_acceder_web -> {
                // Abre la página web
                val url = "https://soymotor.com/"
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(url)
                }
                startActivity(intent)
                true
            }
            R.id.menu_acerca_de -> {

                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}