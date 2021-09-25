package com.example.coinfolio

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coinfolio.data.repository.CryptoCurrencyRepository
import com.example.coinfolio.data.rest.cryptocompare.api.CryptoCompareService


class CreateCryptoCurrencyViewModelProviderFactory(val app: CoinFolioApp, val intent: Intent) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val cryptoCurrencyDao = CoinFolioApp.coinFolioDb.cryptoCurrencyDao()
        val cryptoCurrencyService = CoinFolioApp.retrofit.create(CryptoCompareService::class.java)
        val cryptoCurrencyRepository = CryptoCurrencyRepository(
            app.applicationContext,
            cryptoCurrencyDao,
            cryptoCurrencyService
        )
        val viewModel = CreateCryptoCurrencyViewModel(cryptoCurrencyRepository)
        return viewModel as T
    }
}
