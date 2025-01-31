package com.haris.core.data.network

import com.haris.core.data.response.PeopleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("people")
    suspend fun getPeople(
        @Query("page") page: Int,
        @Query("format") format: String = "json"
    ): PeopleResponse
}