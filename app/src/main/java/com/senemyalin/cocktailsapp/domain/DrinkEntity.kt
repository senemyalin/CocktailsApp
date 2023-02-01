package com.senemyalin.cocktailsapp.domain

data class DrinkEntity(
    val name: String,
    val category: String,
    val videoUrl: String?,
    val imageUrl: String,
    val ingredients: ArrayList<String?>,
)