package com.example.coinfolio

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.coinfolio.data.models.app.CryptoCurrency
import com.example.coinfolio.data.repository.CryptoCurrencyRepository
import com.example.coinfolio.data.rest.cryptocompare.models.CryptoCompareResponse
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import java.util.logging.Logger

class CreateCryptoCurrencyViewModel(private val cryptoCurrencyRepository: CryptoCurrencyRepository) :
    ViewModel() {

    fun loadCryptoCurrencies() {
//        try {
//            var scope = viewModelScope.launch {
//                mAllCryptoCurrencies = liveData {  }cryptoCurrencyRepository.getCryptoCurrencies()
//            }
//        } catch (e: Exception) {
//            Log.e("Load Cryptos", e.message!!)
//        }
    }

    val mAllCryptoCurrencies : LiveData<List<CryptoCurrency>> = liveData {
        emit(cryptoCurrencyRepository.getCryptoCurrencies())
    }
    lateinit var mAllCryptoCurrencies2 : List<CryptoCurrency>


}
