package com.maestre.gridpedia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPiloto(private val listaPilotos: List<Piloto>) :
    RecyclerView.Adapter<AdaptadorPiloto.PilotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PilotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_piloto, parent, false)
        return PilotoViewHolder(view)
    }

    //vinculamos los datos con los elementos del layout
    override fun onBindViewHolder(holder: PilotoViewHolder, posicion: Int) {
        val piloto = listaPilotos[posicion]
        holder.posicion.text = piloto.posicion.toString()
        holder.nombre.text = piloto.nombre
        holder.puntos.text = piloto.puntos.toString()
    }

    //conteo de los pilotos que tenemos
    override fun getItemCount(): Int = listaPilotos.size

    //vinculamos los elementos del layout con los datos
    class PilotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posicion: TextView = itemView.findViewById(R.id.txtPosicion)
        val nombre: TextView = itemView.findViewById(R.id.txtNombre)
        val puntos: TextView = itemView.findViewById(R.id.txtPuntos)
    }
}
