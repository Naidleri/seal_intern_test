package com.haris.starwars_character.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haris.starwars_character.ui.theme.Starwars_characterTheme

@Composable
fun CardPeople(
    name: String,
    height: Int,
    modifier: Modifier = Modifier,
    onClick : () -> Unit
) {
    Card (
        border = BorderStroke(1.dp,MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ){
     Column (
         modifier = modifier
             .padding(12.dp)
     ) {
         Text(
             text = name,
             color = MaterialTheme.colorScheme.onBackground
         )
         Text(
             text = "$height cm",
             color = MaterialTheme.colorScheme.onBackground
         )
     }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCharacterPrev() {
    Starwars_characterTheme {
        CardPeople(
            name = "Luke Skywalker",
            height = 172,
            onClick = {}
        )
    }
}