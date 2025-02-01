package com.haris.core.domain.usecase.people

import androidx.paging.PagingData
import com.haris.core.domain.model.People
import kotlinx.coroutines.flow.Flow

interface PeopleUseCase {
    fun getPeople(): Flow<PagingData<People>>
}