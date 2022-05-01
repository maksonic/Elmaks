package ru.maksonic.elmaks.feature.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.maksonic.elmaks.domain.CitiesRepository
import ru.maksonic.elmaks.domain.CityDomain
import javax.inject.Inject

/**
 * @author makosnic on 01.05.2022
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repo: CitiesRepository) : ViewModel() {

    private var _list = mutableStateOf(emptyList<CityDomain>())
    val list: State<List<CityDomain>> = _list

    init {
        viewModelScope.launch {
            val data = repo.fetchCitiesList()
            _list.value = data
        }

    }
}