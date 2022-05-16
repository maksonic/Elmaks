package ru.maksonic.elmaks.core.store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author maksonic on 01.05.2022
 */
interface AppThemeSetting {
    var currentTheme: MutableStateFlow<Boolean>
    suspend fun setTheme(isDarkMode: Boolean)
    suspend fun readTheme(): MutableStateFlow<Boolean>

    class Base @Inject constructor(
        private val context: Context,
        private val dataStore: AppDataStore
    ) : AppThemeSetting {
        companion object {
            private val KEY_STORE_THEME = booleanPreferencesKey("prefs_setting_theme")
            private const val IS_DARK_MODE = false
        }

        override var currentTheme: MutableStateFlow<Boolean> = MutableStateFlow(IS_DARK_MODE)

        override suspend fun setTheme(isDarkMode: Boolean) {
            dataStore.apply {
                context.datastore.edit { preferences ->
                    preferences[KEY_STORE_THEME] = isDarkMode
                }
            }
        }

        override suspend fun readTheme(): MutableStateFlow<Boolean> {
            dataStore.apply {
                context.datastore.data.map { preferences ->
                    preferences[KEY_STORE_THEME] ?: IS_DARK_MODE
                }.collect {
                    currentTheme.value = it
                }
                return currentTheme
            }
        }
    }
}