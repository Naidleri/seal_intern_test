package com.haris.starwars_character.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isFilled: Boolean = true,
    hasBorder: Boolean = false,
    borderWidth: Dp = 1.dp,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    filledColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isFilled) filledColor else Color.Transparent,
            contentColor = if (isFilled) textColor else borderColor
        ),
        border = if (hasBorder) BorderStroke(borderWidth, borderColor) else null
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Medium
        )
    }
}