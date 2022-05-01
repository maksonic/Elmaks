package ru.maksonic.elmaks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import ru.maksonic.elmaks.core.ui.theme.ElmaksTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ElmaksTheme {
                Screen()
            }
        }
    }
}

@Composable
fun Screen() {
    Scaffold(){

    }
}