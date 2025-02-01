package com.haris.starwars_character.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.haris.core.domain.model.HeightFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeightFilterBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onHeightSelected: (HeightFilter?) -> Unit
) {
    var selectedHeight by remember { mutableStateOf<HeightFilter?>(null) }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = rememberModalBottomSheetState()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Filter Tinggi",
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(bottom = 16.dp)
            )

            HeightFilter.entries.forEach { height ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedHeight = if (selectedHeight == height) null else height
                        }
                        .padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        selected = selectedHeight == height,
                        onClick = {
                            selectedHeight = if (selectedHeight == height) null else height
                        }
                    )
                    Text(
                        text = when (height) {
                            HeightFilter.DIBAWAH_160 -> "Below 160"
                            HeightFilter.ANTARA_160_180 -> "160 - 180"
                            HeightFilter.DIATAS_180 -> "Above 180"
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = modifier.padding(start = 8.dp)
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                CustomButton(
                    text = "Hapus Filter",
                    onClick = {
                        selectedHeight = null
                        onHeightSelected(null)
                        onDismissRequest()
                    },
                    isFilled = false,
                    hasBorder = true,
                    borderColor = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )
                CustomButton(
                    text = "Terapkan",
                    onClick = {
                        onHeightSelected(selectedHeight)
                        onDismissRequest()
                    },
                    isFilled = true,
                    filledColor = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}