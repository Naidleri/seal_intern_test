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
import com.haris.core.domain.model.GenderFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderFilterBottomSheet(
    onDismissRequest: () -> Unit,
    onGenderSelected: (GenderFilter?) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedGender by remember { mutableStateOf<GenderFilter?>(null) }

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
                text = "Filter Gender",
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.padding(bottom = 16.dp)
            )

            GenderFilter.entries.forEach { gender ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedGender = if (selectedGender == gender) null else gender
                        }
                        .padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        selected = selectedGender == gender,
                        onClick = {
                            selectedGender = if (selectedGender == gender) null else gender
                        }
                    )
                    Text(
                        text = gender.name,
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
                        selectedGender = null
                        onGenderSelected(null)
                        onDismissRequest()
                    },
                    isFilled = false,
                    hasBorder = true,
                    borderColor = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                        .padding(vertical = 4.dp)
                )
                CustomButton(
                    text = "Terapkan",
                    onClick = {
                        onGenderSelected(selectedGender)
                        onDismissRequest()
                    },
                    isFilled = true,
                    filledColor = MaterialTheme.colorScheme.primary,
                    textColor = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.weight(1f)
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}