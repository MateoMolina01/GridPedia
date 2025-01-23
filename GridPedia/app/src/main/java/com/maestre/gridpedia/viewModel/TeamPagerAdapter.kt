package com.maestre.gridpedia.viewModel

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maestre.gridpedia.databinding.ItemTeamPageBinding
import com.maestre.gridpedia.model.Team

class TeamPagerAdapter(private val context: Context, private val teams: List<Team>) :
    RecyclerView.Adapter<TeamPagerAdapter.TeamViewHolder>() {

        //le paso los valores de cada equipo
    inner class TeamViewHolder(val binding: ItemTeamPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: Team) {
            binding.tvTeamName.text = team.nombre
            binding.tvTeamPoints.text = "Puntos: ${team.puntos}"
            binding.tvTeamDrivers.text = "Pilotos: ${team.pilotos}"
            binding.root.setBackgroundColor(context.getColor(team.color))

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = ItemTeamPageBinding.inflate(LayoutInflater.from(context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teams[position])

        // Configurar acción del botón "Volver al Inicio"
        holder.binding.btnBackToMain.setOnClickListener {
            val activity = context as? Activity
            activity?.finish() // Finaliza la actividad actual y vuelve a la pantalla principal
        }
    }

    override fun getItemCount(): Int = teams.size

}
