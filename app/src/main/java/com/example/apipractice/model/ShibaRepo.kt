package com.example.apipractice.model

import com.example.apipractice.model.remote.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ShibaRepo {
    private val shibeService by lazy { RetrofitInstance().shibaService }
    suspend fun getShibes(count: Int = 10) = withContext(Dispatchers.IO) {shibeService.getShibeUrls(count)}
}