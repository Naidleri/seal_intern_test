package com.haris.starwars_character.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CardCharacter(
    name: String,
    height: Int,
    modifier: Modifier = Modifier,
    onClick : () -> Unit
) {
    Card (
        modifier = modifier
    ){
     Column (
         modifier = modifier
     ) {
         Text(
             text = name
         )
         Text(
             text = height.toString()
         )
     }
    }
}