package com.maestre.gridpedia

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.maestre.gridpedia.databinding.ActivityDriversStandingsBinding

class DriversStandingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDriversStandingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriversStandingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar la Toolbar
        val toolbar = binding.materialToolbar
        setSupportActionBar(toolbar)

        // Crear una lista de pilotos hardcodeada
        val driversList = listOf(
            Piloto(1, "Lewis Hamilton", 255),
            Piloto(2, "Max Verstappen", 245),
            Piloto(3, "Charles Leclerc", 199),
            Piloto(4, "Sergio Pérez", 180),
            Piloto(5, "Lando Norris", 160),
            Piloto(6, "George Russell", 150),
            Piloto(7, "Carlos Sainz", 140),
            Piloto(8, "Fernando Alonso", 135),
            Piloto(9, "Esteban Ocon", 125),
            Piloto(10, "Pierre Gasly", 120),
            Piloto(11, "Valtteri Bottas", 110),
            Piloto(12, "Sebastian Vettel", 105),
            Piloto(13, "Daniel Ricciardo", 95),
            Piloto(14, "Kevin Magnussen", 90),
            Piloto(15, "Yuki Tsunoda", 85),
            Piloto(16, "Mick Schumacher", 80),
            Piloto(17, "Lance Stroll", 75),
            Piloto(18, "Zhou Guanyu", 70),
            Piloto(19, "Nicholas Latifi", 60),
            Piloto(20, "Alexander Albon", 50)
        )

        //solo para ponerlo mas bonito con separadores
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.recyclerViewDrivers.addItemDecoration(dividerItemDecoration)

        // Configurar RecyclerView
        val adapter = AdaptadorPiloto(driversList)
        binding.recyclerViewDrivers.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewDrivers.adapter = adapter

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mi_menu, menu)
        return true
    }

    // Manejar la selección de elementos del menú
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
