package com.haris.core.domain.repository

import androidx.paging.PagingData
import com.haris.core.domain.model.People
import kotlinx.coroutines.flow.Flow

interface IPeopleRepository {
    fun getPeople(): Flow<PagingData<People>>
}