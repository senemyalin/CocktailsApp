package com.senemyalin.cocktailsapp.ui.home

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senemyalin.cocktailsapp.R
import com.senemyalin.cocktailsapp.common.DrinkListMapper
import com.senemyalin.cocktailsapp.common.NetworkResponse
import com.senemyalin.cocktailsapp.domain.DrinkEntity
import com.senemyalin.cocktailsapp.domain.GetDrinksWithFirstLetterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDrinkWithFirstLetterUseCase: GetDrinksWithFirstLetterUseCase,
    private val drinkListMapper: DrinkListMapper<DrinkEntity, HomeUiData>
) :
    ViewModel() {

    private val _drinkHomeUiState = MutableLiveData<HomeUiState>()
    val drinkHomeUiState: LiveData<HomeUiState> get() = _drinkHomeUiState

    fun getDrinksWithFirstLetter(firstLetter: String) {
        viewModelScope.launch {
            //collectlatest -> eger 2 defa emit edilirse ilk emit iptal olur 2. emit calisir. ilk emit bitmeden 2.ye gecilir
            //collect -> tum emitler calisir, ilk emitin bitmesini bekler sonra digerine gecer.
            getDrinkWithFirstLetterUseCase(firstLetter).collectLatest {
                when (it) {
                    is NetworkResponse.Error -> {
                        _drinkHomeUiState.postValue(HomeUiState.Error(R.string.error))
                    }
                    NetworkResponse.Loading -> {
                        _drinkHomeUiState.postValue(HomeUiState.Loading)
                    }
                    is NetworkResponse.Success -> {
                        val mappedData = drinkListMapper.map(it.result)
                        _drinkHomeUiState.postValue(HomeUiState.Success(mappedData))
                    }
                }
            }
        }
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val data: List<HomeUiData>) : HomeUiState()
    data class Error(@StringRes val message: Int) : HomeUiState()
}

data class HomeUiData(
    val name: String,
    val ingredients: ArrayList<String?>,
    val videoUrl: String?,
    val imageUrl: String
)