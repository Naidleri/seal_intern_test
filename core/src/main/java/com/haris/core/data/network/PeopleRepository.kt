package com.haris.core.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.haris.core.domain.model.People
import com.haris.core.domain.repository.IPeopleRepository
import kotlinx.coroutines.flow.Flow

class PeopleRepository (
    private val apiService: ApiService
): IPeopleRepository {
    override fun getPeople(): Flow<PagingData<People>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PeoplePagingSource(apiService) }
        ).flow
    }

}