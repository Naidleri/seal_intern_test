package com.haris.starwars_character.ui.presentation.detail

import android.content.Intent
import android.provider.Settings
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.haris.core.domain.model.People
import com.haris.starwars_character.ui.component.DetailItem
import com.haris.starwars_character.ui.component.ErrorModalBottomSheet
import com.haris.starwars_character.ui.component.LoadingScreen
import com.haris.starwars_character.ui.presentation.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    url: String,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {
    val people by viewModel.peopleId.collectAsState()
    val context = LocalContext.current
    var showErrorSheet by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    var hasError by remember { mutableStateOf(false) }

    LaunchedEffect(url) {
        isLoading = true
        hasError = false
        viewModel.loadPeopleByUrl(url)
    }

    LaunchedEffect(people) {
        if (people == null && !isLoading) {
            hasError = true
            showErrorSheet = true
        }
        isLoading = false
    }

    BackHandler {
        if (showErrorSheet) {
            showErrorSheet = false
        } else {
            navigateBack()
        }
    }

    if (isLoading) {
        LoadingScreen()
    }

    if (showErrorSheet) {
        ErrorModalBottomSheet(
            isVisible = true,
            onDismiss = { showErrorSheet = false },
            message = "Gagal mengambil data, coba lagi",
            onRetry = {
                isLoading = true
                viewModel.loadPeopleByUrl(url)
            },
            onCancel = {
                val intent = Intent(Settings.ACTION_SETTINGS)
                context.startActivity(intent)
            }
        )
    }

    if (!hasError && people != null) {
        DetailContent(people = people!!, modifier = modifier)
    }
}


@Composable
fun DetailContent(
    people: People,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = people.name ?: "Unknown",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = modifier.padding(vertical = 42.dp,horizontal = 16.dp)
        )

        DetailItem(label = "Gender", value = people.gender)
        DetailItem(label = "Height", value = people.height)
        DetailItem(label = "Mass", value = people.mass)
        DetailItem(label = "Hair Color", value = people.hairColor)
        DetailItem(label = "Eye Color", value = people.eyeColor)
        DetailItem(label = "Birth Year", value = people.birthYear)
        DetailItem(label = "Homeworld", value = people.homeworld)
        DetailItem(label = "Skin Color", value = people.skinColor)

        Spacer(modifier = modifier.height(16.dp))

        Text(
            text = "Films",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
        if (people.films.isNullOrEmpty()) {
            Text(
                text = "Unknown",
                color = MaterialTheme.colorScheme.onBackground,
                modifier = modifier.padding(vertical = 4.dp, horizontal = 16.dp)
            )
        } else {
            people.films!!.forEach { film ->
                Text(
                    text = film ?: "Unknown",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = modifier.padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
        }

        Spacer(modifier = modifier.height(16.dp))

        Text(
            text = "Vehicles",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
        if (people.vehicles.isNullOrEmpty()) {
            Text(
                text = "Unknown",
                color = MaterialTheme.colorScheme.onBackground,
                modifier = modifier.padding(vertical = 4.dp, horizontal = 16.dp)
            )
        } else {
            people.vehicles!!.forEach { vehicle ->
                Text(
                    text = vehicle ?: "Unknown",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = modifier.padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
        }

        Spacer(modifier = modifier.height(16.dp))

        Text(
            text = "Starships",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
        if (people.starships.isNullOrEmpty()) {
            Text(
                text = "Unknown",
                color = MaterialTheme.colorScheme.onBackground,
                modifier = modifier.padding(vertical = 4.dp, horizontal = 16.dp)
            )
        } else {
            people.starships!!.forEach { starship ->
                Text(
                    text = starship ?: "Unknown",
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = modifier.padding(vertical = 4.dp, horizontal = 16.dp)
                )
            }
        }
    }
}