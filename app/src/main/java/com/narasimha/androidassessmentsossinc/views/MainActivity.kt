package com.narasimha.androidassessmentsossinc.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.narasimha.androidassessmentsossinc.data.network.RetrofitClient
import com.narasimha.androidassessmentsossinc.data.repository.CountryRepository
import com.narasimha.androidassessmentsossinc.databinding.ActivityMainBinding
import com.narasimha.androidassessmentsossinc.viewmodel.CountryViewModel
import com.narasimha.androidassessmentsossinc.viewmodel.CountryViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val countryViewModel: CountryViewModel by viewModels {
        CountryViewModelFactory(CountryRepository(RetrofitClient.countryApiService))
    }

    private lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        countryAdapter = CountryAdapter()
        binding.recyclerView.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        countryViewModel.countries.observe(this) { countries ->
            binding.progressBar.visibility = android.view.View.GONE
            binding.recyclerView.visibility = android.view.View.VISIBLE

            countryAdapter.submitList(countries)
        }

        countryViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = android.view.View.VISIBLE
                binding.recyclerView.visibility = android.view.View.GONE
                binding.errorTextView.visibility = android.view.View.GONE
            } else {
                binding.progressBar.visibility = android.view.View.GONE
            }
        }

        countryViewModel.error.observe(this) { errorMessage ->
            if (errorMessage != null) {
                binding.progressBar.visibility = android.view.View.GONE
                binding.recyclerView.visibility = android.view.View.GONE
                binding.errorTextView.visibility = android.view.View.VISIBLE
                binding.errorTextView.text = errorMessage
            }
        }

        countryViewModel.loadCountries()
    }
}
