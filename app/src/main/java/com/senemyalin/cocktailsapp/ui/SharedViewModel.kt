package com.senemyalin.cocktailsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.senemyalin.cocktailsapp.ui.home.HomeUiData

class SharedViewModel: ViewModel(){
    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int> get() = _position

    private val _drinkDetails = MutableLiveData<HomeUiData>()
    val drinkDetails: LiveData<HomeUiData> get()= _drinkDetails

    private val _drinks = MutableLiveData<List<HomeUiData>>()
    val drinks: LiveData<List<HomeUiData>> get()= _drinks

    fun updatePosition(position: Int){
        _position.value = position
    }

    fun updateDrinkDetails(homeUiData: HomeUiData){
        _drinkDetails.value = homeUiData
    }

    fun updateDrinkDetails(homeUiDataList: List<HomeUiData>){
        _drinks.value = homeUiDataList
    }
}