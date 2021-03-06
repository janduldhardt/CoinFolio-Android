package com.example.coinfolio.representation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.coinfolio.CoinFolioApp.Companion.walletId
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.dto.TransactionDTO
import com.example.coinfolio.data.dto.TransferTypeEnum
import com.example.coinfolio.data.relation.WalletWithTransactions
import com.example.coinfolio.data.repository.CryptoCurrencyRepository
import com.example.coinfolio.data.repository.WalletRepository
import com.example.coinfolio.utils.Converters
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.time.ZonedDateTime

class MainViewModel(
    private val cryptoCurrencyRepository: CryptoCurrencyRepository,
    private val walletRepository: WalletRepository
) : ViewModel() {
    val mAllCryptoCurrenciesDTO: LiveData<List<CryptoCurrencyDTO>> =
        cryptoCurrencyRepository.getCryptoCurrencies()


    val mWalletWithTransactions: LiveData<WalletWithTransactions> =
        walletRepository.getWalletWithTransactions(walletId)

    init {
        walletRepository.addValueEventListenerFirebase(walletId)
    }

    fun saveTransaction(
        abbreviation: String,
        amount: String,
        price: String,
        transferTypeEnum: TransferTypeEnum
    ) {
        val transaction = TransactionDTO(
            0,
            walletId,
            abbreviation,
            transferTypeEnum,
            BigDecimal(amount),
            BigDecimal(price),
            Converters().zonedDateTimeToString(ZonedDateTime.now())
        )
        viewModelScope.launch {
            walletRepository.createTransaction(transaction)
        }
    }
}