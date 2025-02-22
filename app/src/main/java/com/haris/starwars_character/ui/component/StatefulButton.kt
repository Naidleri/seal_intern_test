package com.haris.starwars_character.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haris.starwars_character.ui.theme.Starwars_characterTheme

@Composable
fun StatefulButton(
    modifier: Modifier = Modifier,
    initialState: Boolean = false,
    textOn: String,
    textOff: String,
    onStateChanged: (Boolean) -> Unit,
) {
    var isToggled by remember { mutableStateOf(initialState) }

    Button(
        onClick = {
            isToggled = !isToggled
            onStateChanged(isToggled)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isToggled) MaterialTheme.colorScheme.primary else Color.Transparent,
            contentColor = if (isToggled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
        ),
        border = BorderStroke(
            width = if (isToggled) 0.dp else 1.dp,
            color = if (isToggled) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    ) {
        Text(
            text = if (isToggled) textOn else textOff,
            fontWeight = if (isToggled) FontWeight.Medium else FontWeight.Normal
        )
    }
}

@Preview
@Composable
private fun StatefulButtonPrev() {
    Starwars_characterTheme {
        StatefulButton(
            onStateChanged = {},
            textOn = "Enable",
            textOff = "Disable"
        )
    }

}