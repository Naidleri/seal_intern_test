package com.haris.starwars_character

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.haris.starwars_character.ui.presentation.HomeScreen
import com.haris.starwars_character.ui.theme.Starwars_characterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Starwars_characterTheme {
                HomeScreen()
            }
        }
    }
}
