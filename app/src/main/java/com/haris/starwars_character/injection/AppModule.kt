package com.haris.starwars_character.injection

import com.haris.core.domain.usecase.people.PeopleInteractor
import com.haris.core.domain.usecase.people.PeopleUseCase
import com.haris.starwars_character.ui.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<PeopleUseCase> { PeopleInteractor(get()) }
}

val viewModule = module {
    viewModel { HomeViewModel(get()) }
}