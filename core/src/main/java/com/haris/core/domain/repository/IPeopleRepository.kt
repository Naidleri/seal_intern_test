package com.haris.core.domain.repository

import androidx.paging.PagingData
import com.haris.core.domain.model.People
import kotlinx.coroutines.flow.Flow

interface IPeopleRepository {
    fun getPeople(): Flow<PagingData<People>>
    fun getPeopleByUrl(url: String): Flow<People>
    suspend fun getFilmName(url: String): String
    suspend fun getSpeciesName(url: String): String
    suspend fun getVehicleName(url: String): String
    suspend fun getStarshipName(url: String): String

}