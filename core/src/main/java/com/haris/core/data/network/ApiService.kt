package com.haris.core.data.network

import com.haris.core.data.response.PeopleResponse
import com.haris.core.domain.model.Film
import com.haris.core.domain.model.People
import com.haris.core.domain.model.Species
import com.haris.core.domain.model.Starship
import com.haris.core.domain.model.Vehicle
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("people")
    suspend fun getPeople(
        @Query("page") page: Int,
        @Query("format") format: String = "json"
    ): PeopleResponse

    @GET
    suspend fun getPeopleByUrl(@Url url: String): People

    @GET
    suspend fun getFilms(@Url url: String): Film

    @GET
    suspend fun getSpecies(@Url url: String): Species

    @GET
    suspend fun getVehicles(@Url url: String): Vehicle

    @GET
    suspend fun getStarships(@Url url: String): Starship
}