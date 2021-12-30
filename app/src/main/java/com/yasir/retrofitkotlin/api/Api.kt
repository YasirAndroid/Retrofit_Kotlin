package com.yasir.retrofitkotlin.api

import com.yasir.retrofitkotlin.model.FactsData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("/facts/random")
     fun getData(): Call<FactsData>

}