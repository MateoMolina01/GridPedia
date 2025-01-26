package com.maestre.gridpedia.viewModel

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maestre.gridpedia.databinding.ItemTeamPageBinding
import com.maestre.gridpedia.model.Team

class TeamPagerAdapter(
    private val context: Context,
    private val teams: List<Team> // Lista de pares (nombre, puntos)
) : RecyclerView.Adapter<TeamPagerAdapter.TeamViewHolder>() {

    // ViewHolder para manejar cada elemento del RecyclerView
    inner class TeamViewHolder(val binding: ItemTeamPageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Vincular datos del equipo al layout
        fun bind(team: Team) {
            val (id,nombre, puntos,color) = team // Desestructuración del par (nombre, puntos)
            binding.tvTeamName.text = nombre// Nombre del equipo
            binding.tvTeamPoints.text = "Puntos: $puntos" // Puntos del equipo
        }
    }

    // Crear el ViewHolder inflando el layout correspondiente
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = ItemTeamPageBinding.inflate(LayoutInflater.from(context), parent, false)
        return TeamViewHolder(binding)
    }

    // Enlazar el ViewHolder con los datos
    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teams[position]) // Pasar el par (nombre, puntos) al ViewHolder

        // Configurar acción del botón "Volver al Inicio"
        holder.binding.btnBackToMain.setOnClickListener {
            val activity = context as? Activity
            activity?.finish() // Finaliza la actividad actual y vuelve a la pantalla principal
        }
    }

    // Obtener el tamaño de la lista
    override fun getItemCount(): Int = teams.size
}
