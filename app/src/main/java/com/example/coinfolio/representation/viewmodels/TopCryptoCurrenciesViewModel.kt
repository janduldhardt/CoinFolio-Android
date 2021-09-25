package com.example.coinfolio.representation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.repository.CryptoCurrencyRepository

class TopCryptoCurrenciesViewModel(private val cryptoCurrencyRepository: CryptoCurrencyRepository) :
    ViewModel() {

    val mAllCryptoCurrenciesDTO: LiveData<List<CryptoCurrencyDTO>> = liveData {
        emit(cryptoCurrencyRepository.getCryptoCurrencies())
    }

}
