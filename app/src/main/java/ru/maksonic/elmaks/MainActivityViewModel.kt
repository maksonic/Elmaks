package ru.maksonic.elmaks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.maksonic.elmaks.core.store.AppThemeSetting
import javax.inject.Inject

/**
 * @author maksonic on 02.05.2022
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val themeSetting: AppThemeSetting
) : ViewModel() {

    init {
        viewModelScope.launch {
            themeSetting.readTheme()
        }
    }

    val themeState = themeSetting.currentTheme
}