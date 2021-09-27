package com.example.coinfolio.representation.viewmodels

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coinfolio.CoinFolioApp
import com.example.coinfolio.data.repository.CryptoCurrencyRepository
import com.example.coinfolio.data.repository.WalletRepository
import com.example.coinfolio.data.rest.cryptocompare.api.CryptoCompareService
import com.example.coinfolio.data.services.WalletService

class MainViewModelFactory(val app: CoinFolioApp, val intent: Intent) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val cryptoCurrencyDao = CoinFolioApp.coinFolioDb.cryptoCurrencyDao()
        val walletDao = CoinFolioApp.coinFolioDb.walletDao()
        val transferDao = CoinFolioApp.coinFolioDb.transferDao()
        val cryptoCurrencyService = CoinFolioApp.retrofit.create(CryptoCompareService::class.java)
        val walletService = WalletService()
        val cryptoCurrencyRepository = CryptoCurrencyRepository(
            app.applicationContext,
            cryptoCurrencyDao,
            cryptoCurrencyService
        )

        val walletRepository = WalletRepository(app.applicationContext, walletDao, transferDao, cryptoCurrencyDao, walletService)
        //TODO: Add Transfertypeenum!
        val viewModel = MainViewModel(cryptoCurrencyRepository, walletRepository)
        return viewModel as T
    }
}