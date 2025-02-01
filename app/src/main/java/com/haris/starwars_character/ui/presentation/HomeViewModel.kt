package com.haris.starwars_character.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.haris.core.domain.model.FilterOptions
import com.haris.core.domain.model.GenderFilter
import com.haris.core.domain.model.HeightFilter
import com.haris.core.domain.model.People
import com.haris.core.domain.usecase.people.PeopleUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val peopleUseCase: PeopleUseCase
) : ViewModel() {

    private val currentFilterOptions = MutableStateFlow(FilterOptions())

    @OptIn(ExperimentalCoroutinesApi::class)
    val people: Flow<PagingData<People>> = currentFilterOptions
        .flatMapLatest { filterOptions ->
            peopleUseCase.getPeople(filterOptions)
                .cachedIn(viewModelScope)
        }

    private val _peopleId = MutableStateFlow<People?>(null)
    val peopleId: StateFlow<People?> get() = _peopleId

    fun loadPeopleByUrl(url: String) {
        viewModelScope.launch {
            peopleUseCase.getPeopleByUrl(url).collect { people ->
                _peopleId.value = people
            }
        }
    }

    fun setGenderFilter(gender: GenderFilter?) {
        currentFilterOptions.value = currentFilterOptions.value.copy(gender = gender)
    }

    fun setHeightFilter(height: HeightFilter?) {
        currentFilterOptions.value = currentFilterOptions.value.copy(height = height)
    }

    fun setSearchQuery(query: String?) {
        currentFilterOptions.value = currentFilterOptions.value.copy(searchQuery = query)
    }
}