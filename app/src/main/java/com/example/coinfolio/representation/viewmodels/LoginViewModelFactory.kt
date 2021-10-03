package com.example.coinfolio.representation.viewmodels

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coinfolio.CoinFolioApp
import com.example.coinfolio.data.repository.WalletRepository
import com.example.coinfolio.data.services.WalletService

class LoginViewModelFactory( val app: CoinFolioApp, val intent: Intent) :
ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val cryptoCurrencyDao = CoinFolioApp.coinFolioDb.cryptoCurrencyDao()
        val walletDao = CoinFolioApp.coinFolioDb.walletDao()
        val transferDao = CoinFolioApp.coinFolioDb.transferDao()
        val walletService = WalletService()

        val walletRepository = WalletRepository(app.applicationContext, walletDao, transferDao, cryptoCurrencyDao, walletService)

        val viewModel = LoginViewModel(walletRepository)
        return viewModel as T
    }
}