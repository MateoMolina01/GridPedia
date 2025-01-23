package com.maestre.gridpedia.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.maestre.gridpedia.R
import com.maestre.gridpedia.viewModel.TeamPagerAdapter
import com.maestre.gridpedia.databinding.ActivityTeamPagerBinding
import com.maestre.gridpedia.model.Team

class TeamPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Lista de equipos
        val teams = listOf(
            Team("Red Bull Racing", 696, "Max Verstappen, Sergio Pérez", R.color.redbull),
            Team("Ferrari", 487, "Charles Leclerc, Carlos Sainz", R.color.ferrari),
            Team("Mercedes", 520, "Lewis Hamilton, George Russell", R.color.mercedes),
            Team("Alpine", 235, "Fernando Alonso, Esteban Ocon", R.color.alpine),
            Team("McLaren", 205, "Lando Norris, Daniel Ricciardo", R.color.mclaren),
            Team("Alfa Romeo", 102, "Valtteri Bottas, Zhou Guanyu", R.color.alfaromeo),
            Team("Haas", 90, "Kevin Magnussen, Mick Schumacher", R.color.haas),
            Team("AlphaTauri", 85, "Pierre Gasly, Yuki Tsunoda", R.color.alphatauri),
            Team("Aston Martin", 60, "Sebastian Vettel, Lance Stroll", R.color.astonmartin),
            Team("Williams", 25, "Alexander Albon, Nicholas Latifi", R.color.williams)
        )

        // Configurar el ViewPager
        val adapter = TeamPagerAdapter(this, teams)
        binding.viewPager.adapter = adapter

        // Cambiar color de la toolbar al deslizar
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.toolbar.setBackgroundColor(getColor(teams[position].color))
            }
        })
    }
}
