package com.example.apipractice.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(ShibeService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val shibaService : ShibeService by lazy { retrofit.create() }
}
/*
Todo:
 add a new defaul fragment
    - need a count
    - pass the count to shibe's count
  orginal frag need grid view (2 span)
    - glide(for img)
 */