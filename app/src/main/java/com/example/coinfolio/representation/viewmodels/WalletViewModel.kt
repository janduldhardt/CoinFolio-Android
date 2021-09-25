package com.example.coinfolio.representation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.coinfolio.data.dto.WalletWithTransfersDTO
import com.example.coinfolio.data.repository.CryptoCurrencyRepository
import com.example.coinfolio.data.repository.WalletRepository

class WalletViewModel(private val cryptoCurrencyRepository: CryptoCurrencyRepository, private val walletRepository : WalletRepository) : ViewModel() {
    val mAllCryptoCurrencies: LiveData<WalletWithTransfersDTO> = liveData {
//        emit(cryptoCurrencyRepository.getCryptoCurrencies())
    }
}