package com.maestre.gridpedia.model

data class Team(
    val id: Int,
    val nombre: String,
    val puntos: Int,
    val color: String // ID del color en `R.color`
)
