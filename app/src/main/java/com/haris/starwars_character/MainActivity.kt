package com.haris.starwars_character

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.haris.starwars_character.ui.theme.Starwars_characterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Starwars_characterTheme {
                StarwarsApp()
            }
        }
    }
}
