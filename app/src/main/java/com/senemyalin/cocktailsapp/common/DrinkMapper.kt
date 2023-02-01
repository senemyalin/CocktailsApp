package com.senemyalin.cocktailsapp.common

interface DrinkMapper<I, O> {
    fun map(input: I?): O
}