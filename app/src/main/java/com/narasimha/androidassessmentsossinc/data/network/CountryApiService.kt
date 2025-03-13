package com.narasimha.androidassessmentsossinc.data.network

import com.narasimha.androidassessmentsossinc.data.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryApiService {
    @GET("countries.json")
    suspend fun getCountries(): Response<List<Country>>
}