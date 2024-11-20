package com.maestre.gridpedia

data class Team(
    val nombre: String,
    val puntos: Int,
    val pilotos: String,
    val color: Int // ID del color en `R.color`
)
