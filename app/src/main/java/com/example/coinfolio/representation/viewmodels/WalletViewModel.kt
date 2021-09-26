package com.example.coinfolio.representation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.coinfolio.data.dto.*
import com.example.coinfolio.data.relation.TransactionWithCryptoCurrency
import com.example.coinfolio.data.relation.WalletWithTransactions
import com.example.coinfolio.data.repository.CryptoCurrencyRepository
import com.example.coinfolio.data.repository.WalletRepository
import com.example.coinfolio.utils.Converters
import kotlinx.coroutines.*
import java.math.BigDecimal
import java.time.ZonedDateTime

class WalletViewModel(
    private val cryptoCurrencyRepository: CryptoCurrencyRepository,
    private val walletRepository: WalletRepository,
    private val walletId : String
) : ViewModel() {

    val mAllCryptoCurrenciesDTO: LiveData<WalletWithTransactions> = liveData {
        emit(walletRepository.getWalletWithTransactions(walletId))
    }


    init {
        walletRepository.addValueEventListenerFirebase(walletId)
    }

    // TODO: Reload the wallet when new coin was added
//    fun loadWallet() {
//        mAllCryptoCurrenciesDTO = liveData {
//            emit(walletRepository.getWalletWithTransactions(testWalletId))
//        }
//    }

    fun retrieveTestWallet() {
        viewModelScope.launch {
            val wallet = walletRepository.getWalletWithTransactions(walletId)
            val t = walletRepository.getAllTransactions()
            var s = wallet
            var p = t
        }
    }

    fun createTestWallet() {
        viewModelScope.launch {
            val wallet = WalletDTO(walletId)
            walletRepository.createWallet(wallet)
//            var coin: CryptoCurrencyDTO
//            val coinJob = withContext(context = Dispatchers.Default) {
//                coin = cryptoCurrencyRepository.getCryptoCurrencies().get(0)
//            }
            val transfersDTO = TransactionDTO(
                1,
                walletId,
                "BTC",
                TransferTypeEnum.DEPOSIT,
                BigDecimal(12),
                BigDecimal(10.5),
                Converters().zonedDateTimeToString(ZonedDateTime.now())
            )

            walletRepository.createTransaction(transfersDTO)

//            val cct = TransactionWithCryptoCurrency(transfersDTO, coin)
//            val walletWithTransfers = WalletWithTransactions(wallet, listOf(cct))
//            walletRepository.createWallet(walletWithTransfers)
        }

    }
}