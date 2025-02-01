package com.haris.starwars_character.ui.presentation

import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.haris.core.domain.model.People
import com.haris.starwars_character.ui.component.CardPeople
import com.haris.starwars_character.ui.component.EmptyScreen
import com.haris.starwars_character.ui.component.ErrorModalBottomSheet
import com.haris.starwars_character.ui.component.LoadingScreen
import com.haris.starwars_character.ui.component.LoadingScreenAppend
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    val people = viewModel.people.collectAsLazyPagingItems()
    val context = LocalContext.current
    var showErrorSheet by remember { mutableStateOf(false) }

    ErrorModalBottomSheet(
        isVisible = showErrorSheet,
        onDismiss = { showErrorSheet = false },
        message = "Gagal mengambil data, coba lagi",
        onRetry = { people.retry() },
        onCancel = {
            val intent = Intent(Settings.ACTION_SETTINGS)
            context.startActivity(intent)
        }
    )

    HomeContent(
        people = people,
        modifier = modifier,
        onShowErrorSheet = { showErrorSheet = it },
        onRetry = { people.retry() },
        onSettings = {
            val intent = Intent(Settings.ACTION_SETTINGS)
            context.startActivity(intent)
        }
    )
}

@Composable
fun HomeContent(
    people: LazyPagingItems<People>,
    modifier: Modifier = Modifier,
    onShowErrorSheet: (Boolean) -> Unit,
    onRetry: () -> Unit,
    onSettings: () -> Unit
) {
    val loadState = people.loadState
    var showErrorSheet by remember { mutableStateOf(false) }

    LaunchedEffect(loadState) {
        if (loadState.refresh is LoadState.Error || loadState.append is LoadState.Error) {
            showErrorSheet = true
        }
    }

    if (showErrorSheet) {
        ErrorModalBottomSheet(
            isVisible = showErrorSheet,
            onDismiss = { showErrorSheet = false },
            message = "Gagal mengambil data, coba lagi",
            onRetry = {
                onRetry()
                showErrorSheet = false
            },
            onCancel = {
                onSettings()
                showErrorSheet = false
            }
        )
    }

    when {
        loadState.refresh is LoadState.Loading -> {
            LoadingScreen()
        }
        loadState.refresh is LoadState.NotLoading && people.itemCount == 0 -> {
            EmptyScreen(
                message = "Tidak ada data yang ditemukan",
                modifier = modifier
            )
        }
        else -> {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                items(people.itemCount) { person ->
                    val item = people[person]
                    item?.let {
                        CardPeople(
                            name = it.name ?: "Unknown",
                            height = it.height?.toIntOrNull() ?: 0,
                            onClick = {}
                        )
                    }
                }

                if (loadState.append is LoadState.Loading) {
                    item {
                        LoadingScreenAppend()
                    }
                }
            }
        }
    }
}