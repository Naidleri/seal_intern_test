package com.haris.core.domain.usecase.people

import androidx.paging.PagingData
import androidx.paging.filter
import com.haris.core.domain.model.FilterOptions
import com.haris.core.domain.model.HeightFilter
import com.haris.core.domain.model.People
import com.haris.core.domain.repository.IPeopleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PeopleUseCaseImpl(
    private val peopleRepository: IPeopleRepository
) : PeopleUseCase {
    override fun getPeople(filterOptions: FilterOptions?): Flow<PagingData<People>> {
        return peopleRepository.getPeople()
            .map { pagingData ->
                pagingData.filter { people ->
                    if (filterOptions == null) true
                    else {
                        val genderMatch = filterOptions.gender == null ||
                                people.gender.equals(filterOptions.gender.name, ignoreCase = true)
                        val heightMatch = filterOptions.height == null || when (filterOptions.height) {
                            HeightFilter.DIBAWAH_160 -> (people.height?.toIntOrNull() ?: 0) < 160
                            HeightFilter.ANTARA_160_180 -> (people.height?.toIntOrNull() ?: 0) in 160..180
                            HeightFilter.DIATAS_180 -> (people.height?.toIntOrNull() ?: 0) > 180
                        }
                        val searchMatch = filterOptions.searchQuery == null ||
                                people.name?.contains(filterOptions.searchQuery, ignoreCase = true) == true
                        genderMatch && heightMatch && searchMatch
                    }
                }
            }
    }
}