package com.maestre.gridpedia.viewModel

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maestre.gridpedia.R
import com.maestre.gridpedia.model.Piloto

class AdaptadorPiloto(private val listaPilotos: List<Piloto>) :
    RecyclerView.Adapter<AdaptadorPiloto.PilotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PilotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_piloto, parent, false)
        return PilotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PilotoViewHolder, position: Int) {
        val piloto = listaPilotos[position]
        val contexto = holder.itemView.context

        // Configuramos el nombre y los puntos
        holder.posicion.text = piloto.id.toString()
        holder.nombre.text = piloto.nombre
        holder.puntos.text = "Puntos: ${piloto.puntos}" // Mostrar "Puntos: [número]"

        // Obtener el nombre del recurso desde el atributo imagenEquipo y añadir "_foreground"
        val nombreRecurso = piloto.imagenEquipo // Ejemplo: "ferrari"

        // Verificar que el recurso no sea nulo o vacío y buscar su ID
        if (!nombreRecurso.isNullOrEmpty()) {
            val resId = contexto.resources.getIdentifier(
                "${nombreRecurso}_foreground", // Nombre completo del recurso
                "drawable",                   // Tipo de recurso
                contexto.packageName          // Nombre del paquete
            )

            if (resId != 0) {
                // Si el recurso existe, cargarlo en el ImageView
                holder.imagen.setImageResource(resId)
            } else {
                // Si no existe, usar una imagen predeterminada
                holder.imagen.setImageResource(R.drawable.alpine_foreground)
            }
        } else {
            // Si imagenEquipo está vacío o nulo, usar una imagen predeterminada
            holder.imagen.setImageResource(R.drawable.alpine_foreground)
        }


        // Configurar el texto en los elementos
        holder.nombre.text = piloto.nombre
        holder.puntos.text = "${piloto.puntos}"

        // Configurar la imagen
        val resId = contexto.resources.getIdentifier(
            "${nombreRecurso}_foreground", "drawable", contexto.packageName
        )
        if (resId != 0) {
            holder.imagen.setImageResource(resId)
        } else {
            holder.imagen.setImageResource(R.drawable.ic_launcher_foreground)
        }

        // Configurar el click en el item
        holder.itemView.setOnClickListener {
            // Mostrar un AlertDialog
            AlertDialog.Builder(contexto)
                .setTitle("${piloto.nombre}")
                .setMessage("Puntos: ${piloto.puntos}")
                .setIcon(resId)
                .setPositiveButton("Aceptar") { dialog, _ ->
                    dialog.dismiss() // Cerrar el diálogo
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss() // Cerrar el diálogo
                }
                .show()
        }

    }

    override fun getItemCount(): Int = listaPilotos.size

    // Vinculamos los elementos del layout con los datos
    class PilotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posicion: TextView = itemView.findViewById(R.id.txtPosicion)
        val nombre: TextView = itemView.findViewById(R.id.txtNombre)
        val puntos: TextView = itemView.findViewById(R.id.txtPuntos)
        val imagen: ImageView = itemView.findViewById(R.id.imgPiloto)
    }
}


