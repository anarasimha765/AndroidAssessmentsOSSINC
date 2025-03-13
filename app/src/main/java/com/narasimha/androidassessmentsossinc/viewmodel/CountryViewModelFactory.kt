package com.narasimha.androidassessmentsossinc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.narasimha.androidassessmentsossinc.data.repository.CountryRepository

class CountryViewModelFactory(
    private val countryRepository: CountryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(countryRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}