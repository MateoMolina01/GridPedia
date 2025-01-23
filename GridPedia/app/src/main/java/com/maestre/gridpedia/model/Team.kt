package com.maestre.gridpedia.model

data class Team(
    val nombre: String,
    val puntos: Int,
    val pilotos: String,
    val color: Int // ID del color en `R.color`
)
