package com.maestre.gridpedia.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.maestre.gridpedia.R

class ChangeColorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_color)

        // Referencias a los elementos del layout
        val spinnerColors: Spinner = findViewById(R.id.spinner_colors)
        val btnOk: Button = findViewById(R.id.btn_ok)

        // Opciones de colores
        val colors = arrayOf("Negro", "Azul", "Verde")

        //tengo que meter los colores con hexadecimal porque si llamo al @color se me cierra la app
        val colorValues = arrayOf("#000000", "#0000FF", "#008000")

        // Adaptador para el Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colors)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerColors.adapter = adapter

        // Acción del botón OK
        btnOk.setOnClickListener {
            val selectedColor = colorValues[spinnerColors.selectedItemPosition] // Color seleccionado

            // Enviar el color de vuelta a MainActivity
            val resultIntent = Intent()
            resultIntent.putExtra("SELECTED_COLOR", selectedColor)
            setResult(Activity.RESULT_OK, resultIntent)
            finish() // Finalizar la actividad
        }
    }
}
