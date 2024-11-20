package com.maestre.gridpedia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maestre.gridpedia.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Acción del botón OK
        binding.btnOkAbout.setOnClickListener {
            // Regresar a la pantalla principal
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
            finish() // Cerrar la actividad actual
        }
    }
}
