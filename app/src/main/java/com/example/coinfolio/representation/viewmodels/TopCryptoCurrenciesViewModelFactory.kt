package com.example.coinfolio.representation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coinfolio.CoinFolioApp
import com.example.coinfolio.data.repository.CryptoCurrencyRepository
import com.example.coinfolio.data.rest.cryptocompare.api.CryptoCompareService


class TopCryptoCurrenciesViewModelProviderFactory(val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val cryptoCurrencyDao = CoinFolioApp.coinFolioDb.cryptoCurrencyDao()
        val cryptoCurrencyService = CoinFolioApp.retrofit.create(CryptoCompareService::class.java)
        val cryptoCurrencyRepository = CryptoCurrencyRepository(
            context,
            cryptoCurrencyDao,
            cryptoCurrencyService
        )
        val viewModel = TopCryptoCurrenciesViewModel(cryptoCurrencyRepository)
        return viewModel as T
    }
}
