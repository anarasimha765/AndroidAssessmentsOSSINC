package com.narasimha.androidassessmentsossinc.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.narasimha.androidassessmentsossinc.data.model.Country
import com.narasimha.androidassessmentsossinc.data.repository.CountryRepository
import kotlinx.coroutines.launch

class CountryViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadCountries() {
        _isLoading.value = true
        _error.value = null
        viewModelScope.launch {
            try {
                val countriesList = countryRepository.getCountries()
                _countries.value = countriesList
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
                _error.value = "Error fetching countries: ${e.message}"
            }
        }
    }
}
