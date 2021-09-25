package com.example.coinfolio.representation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.dto.TransactionDTO
import com.example.coinfolio.data.dto.TransferTypeEnum
import com.example.coinfolio.data.relation.TransactionWithCryptoCurrency
import com.example.coinfolio.data.repository.CryptoCurrencyRepository
import com.example.coinfolio.data.repository.WalletRepository
import com.example.coinfolio.utils.SharedPreferencesUtil
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.ZonedDateTime

class TransactionDetailsViewModel(
    private val transferTypeEnum: TransferTypeEnum,
    private val cryptoCurrencyRepository: CryptoCurrencyRepository,
    private val walletRepository: WalletRepository
) : ViewModel() {

    fun saveTransaction(abbreviation: String, amount: String, price: String, walletId : String) {
        val transaction = TransactionDTO(0,walletId,abbreviation,transferTypeEnum,
            BigDecimal(amount), BigDecimal(price), ZonedDateTime.now())
        viewModelScope.launch {
            walletRepository.createTransaction(transaction)
        }
    }


    val mAllCryptoCurrenciesDTO: LiveData<List<CryptoCurrencyDTO>> = liveData {
        emit(cryptoCurrencyRepository.getCryptoCurrenciesLocal())
    }
}