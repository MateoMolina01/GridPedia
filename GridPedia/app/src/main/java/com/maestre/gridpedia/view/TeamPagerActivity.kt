package com.maestre.gridpedia.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.maestre.gridpedia.R
import com.maestre.gridpedia.viewModel.TeamPagerAdapter
import com.maestre.gridpedia.databinding.ActivityTeamPagerBinding
import com.maestre.gridpedia.model.persistencia.AdminSQLiteConexion

class TeamPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Lista de equipos obtenida desde la base de datos
        val teams = AdminSQLiteConexion(this).obtenerEquipos(this)

        // Configurar el ViewPager2 con el adaptador
        val adapter = TeamPagerAdapter(this, teams)
        binding.viewPager.adapter = adapter

        // Vincular el TabLayout con el ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = teams[position].nombre // Configurar el título de cada tab
        }.attach()

        // Cambiar colores dinámicamente al deslizar entre páginas
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val colorName = teams[position].color // El nombre del color (definido en colors.xml)

                // Buscar el ID del recurso de color en colors.xml
                val resId = resources.getIdentifier(colorName, "color", packageName)

                if (resId != 0) {
                    // Si el recurso existe, cambiar el color de la Toolbar y del fondo
                    binding.toolbar.setBackgroundColor(getColor(resId))
                    binding.root.setBackgroundColor(getColor(resId))
                } else {
                    // Si no existe, usar un color predeterminado
                    val defaultColor = getColor(R.color.black)
                    binding.toolbar.setBackgroundColor(defaultColor)
                    binding.root.setBackgroundColor(defaultColor)
                }
            }
        })
    }
}
