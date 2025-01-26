package com.maestre.gridpedia.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.maestre.gridpedia.viewModel.AdaptadorPiloto
import com.maestre.gridpedia.R
import com.maestre.gridpedia.databinding.ActivityDriversStandingsBinding
import com.maestre.gridpedia.model.persistencia.AdminSQLiteConexion

class DriversStandingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDriversStandingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var dbHelper = AdminSQLiteConexion(this)
        binding = ActivityDriversStandingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Configurar la Toolbar
        val toolbar = binding.materialToolbar
        setSupportActionBar(toolbar)

        // Crear una lista de pilotos hardcodeada
        val driversList = AdminSQLiteConexion(this).obtenerPilotos(this);


        //solo para ponerlo mas bonito con separadores
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.recyclerViewDrivers.addItemDecoration(dividerItemDecoration)

        // Configurar RecyclerView
        val adapter = AdaptadorPiloto(driversList)
        binding.recyclerViewDrivers.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewDrivers.adapter = adapter
        
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mi_menu, menu)
        return true
    }

    // Manejar la selección de elementos del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_cambiar_tema -> {

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
