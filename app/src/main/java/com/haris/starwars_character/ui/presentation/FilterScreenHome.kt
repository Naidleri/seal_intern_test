package com.haris.starwars_character.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.haris.starwars_character.ui.component.DropdownButton
import com.haris.starwars_character.ui.component.GenderFilterBottomSheet
import com.haris.starwars_character.ui.component.HeightFilterBottomSheet

@Composable
fun FilterScreenHome(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    var showGenderBottomSheet by remember { mutableStateOf(false) }
    var showHeightBottomSheet by remember { mutableStateOf(false) }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        item {
            DropdownButton(
                textOn = "Filter Gender",
                textOff = "Filter Gender",
                onStateChanged = { showGenderBottomSheet = it }
            )
        }

        item {
            DropdownButton(
                textOn = "Filter Height",
                textOff = "Filter Height",
                onStateChanged = { showHeightBottomSheet = it }
            )
        }
    }

    if (showGenderBottomSheet) {
        GenderFilterBottomSheet(
            onDismissRequest = { showGenderBottomSheet = false },
            onGenderSelected = { gender ->
                viewModel.setGenderFilter(gender)
            }
        )
    }

    if (showHeightBottomSheet) {
        HeightFilterBottomSheet(
            onDismissRequest = { showHeightBottomSheet = false },
            onHeightSelected = { height ->
                viewModel.setHeightFilter(height)
            }
        )
    }
}