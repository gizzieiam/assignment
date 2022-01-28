package com.example.apipractice.model.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ShibeService {
    companion object{
        const val BASE_URL = "http://shibe.online"
    }
    @GET("/api/shibes")
    suspend fun getShibeUrls(
        @Query("count") count: Int
    ) : List<String>
}