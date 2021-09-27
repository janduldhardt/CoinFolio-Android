package com.example.coinfolio.representation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.coinfolio.CoinFolioApp.Companion.walletId
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.relation.WalletWithTransactions
import com.example.coinfolio.data.repository.CryptoCurrencyRepository
import com.example.coinfolio.data.repository.WalletRepository

class MainViewModel(
    cryptoCurrencyRepository: CryptoCurrencyRepository,
    walletRepository: WalletRepository
) : ViewModel() {
    val mAllCryptoCurrenciesDTO: LiveData<List<CryptoCurrencyDTO>> = liveData {
        emit(cryptoCurrencyRepository.getCryptoCurrencies())
    }

    val mWalletWithTransactions: LiveData<WalletWithTransactions> = liveData {
        emit(walletRepository.getWalletWithTransactions(walletId))
    }
}