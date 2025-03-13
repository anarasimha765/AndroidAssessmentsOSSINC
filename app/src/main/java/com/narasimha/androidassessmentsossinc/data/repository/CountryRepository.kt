package com.narasimha.androidassessmentsossinc.data.repository

import com.narasimha.androidassessmentsossinc.data.model.Country
import com.narasimha.androidassessmentsossinc.data.network.CountryApiService

class CountryRepository(private val countryApiService: CountryApiService) {

    suspend fun getCountries(): List<Country> {
        val response = countryApiService.getCountries()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Failed to load countries")
        }
    }
}
