package com.haris.starwars_character.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.haris.core.domain.model.People
import com.haris.core.domain.usecase.people.PeopleUseCase
import kotlinx.coroutines.flow.Flow

class HomeViewModel (
    private val peopleUseCase: PeopleUseCase
): ViewModel() {
    val people : Flow<PagingData<People>> = peopleUseCase.getPeople()
}