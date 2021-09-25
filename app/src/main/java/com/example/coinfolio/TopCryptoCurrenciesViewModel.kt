package com.example.coinfolio

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.coinfolio.data.models.app.CryptoCurrency
import com.example.coinfolio.data.repository.CryptoCurrencyRepository

class TopCryptoCurrenciesViewModel(private val cryptoCurrencyRepository: CryptoCurrencyRepository) :
    ViewModel() {

    val mAllCryptoCurrencies: LiveData<List<CryptoCurrency>> = liveData {
        emit(cryptoCurrencyRepository.getCryptoCurrencies())
    }

}
