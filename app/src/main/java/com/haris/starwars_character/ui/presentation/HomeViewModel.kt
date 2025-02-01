package com.haris.starwars_character.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.haris.core.domain.model.FilterOptions
import com.haris.core.domain.model.GenderFilter
import com.haris.core.domain.model.HeightFilter
import com.haris.core.domain.model.People
import com.haris.core.domain.usecase.people.PeopleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val peopleUseCase: PeopleUseCase
) : ViewModel() {
    private val _people = MutableStateFlow<PagingData<People>>(PagingData.empty())
    val people: StateFlow<PagingData<People>> get() = _people

    private val currentFilterOptions = MutableStateFlow(FilterOptions())

    init {
        loadPeople()
    }

    fun loadPeople() {
        viewModelScope.launch {
            peopleUseCase.getPeople(currentFilterOptions.value)
                .collect { pagingData ->
                    _people.value = pagingData
                }
        }
    }

    fun setGenderFilter(gender: GenderFilter?) {
        currentFilterOptions.value = currentFilterOptions.value.copy(gender = gender)
        loadPeople()
    }

    fun setHeightFilter(height: HeightFilter?) {
        currentFilterOptions.value = currentFilterOptions.value.copy(height = height)
        loadPeople()
    }

    fun setSearchQuery(query: String?) {
        currentFilterOptions.value = currentFilterOptions.value.copy(searchQuery = query)
        loadPeople()
    }
}