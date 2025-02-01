package com.haris.core.data.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.haris.core.domain.model.People
import com.haris.core.domain.repository.IPeopleRepository
import com.haris.core.utils.PeopleMapper.mapResultsItemToPeople
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

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

    override fun getPeopleByUrl(url: String): Flow<People> = flow {
        val people = apiService.getPeopleByUrl(url)

        // Fetch names for all related items
        val films = people.films?.map { filmUrl ->
            try {
                filmUrl?.let { getFilmName(it) }
            } catch (e: Exception) {
                "Unknown Film"
            }
        }

        val vehicles = people.vehicles?.map { vehicleUrl ->
            try {
                vehicleUrl?.let { getVehicleName(it) }
            } catch (e: Exception) {
                "Unknown Vehicle"
            }
        }

        val starships = people.starships?.map { starshipUrl ->
            try {
                starshipUrl?.let { getStarshipName(it) }
            } catch (e: Exception) {
                "Unknown Starship"
            }
        }

        emit(people.copy(
            films = films,
            vehicles = vehicles,
            starships = starships
        ))
    }

    override suspend fun getFilmName(url: String): String {
        return apiService.getFilms(url).title
    }

    override suspend fun getSpeciesName(url: String): String {
        return apiService.getSpecies(url).name
    }

    override suspend fun getVehicleName(url: String): String {
        return apiService.getVehicles(url).name
    }

    override suspend fun getStarshipName(url: String): String {
        return apiService.getStarships(url).name
    }
}