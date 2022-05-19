package ru.maksonic.elmaks.feature.details

import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.maksonic.elmaks.domain.FetchCityDetailsUseCase
import ru.maksonic.elmaks.navigation.api.Navigator
import javax.inject.Inject

/**
 * @Author maksonic on 18.05.2022
 */
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val fetchCityDetailsUseCase: FetchCityDetailsUseCase
): ViewModel() {
  /*  private var _cityName: MutableStateFlow<String> = MutableStateFlow("")
    val cityName: String = _cityName.value
    init {
        viewModelScope.launch {
            val cityId = navigator.fetchPassedData()
            fetchCityDetailsUseCase(cityId).collect {
                it.onSuccess { cityDomain -> _cityName.value = cityDomain.name }
            }
        }
    }*/
  private val _cityName: MutableStateFlow<String> = MutableStateFlow("")
    val cityName: StateFlow<String>
        get() = _cityName

    init {
        val id = navigator.fetchPassedData()
        _cityName.value = id.toString()
       // Log.e("@@@@", "$id")
     //  val cityName = fetchCityDetailsUseCase.invoke(id)
       /* viewModelScope.launch {
            cityName.collect {
                it.onSuccess { cityDomain -> _cityName.value = cityDomain.name  }
                it.onFailure { _cityName.value = "FAIL" }
            }
        }*/
    }
    fun backPressed() = navigator.backPressed()

}