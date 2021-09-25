package com.example.coinfolio

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinfolio.data.models.app.CryptoCurrency
import com.example.coinfolio.data.repository.CryptoCurrencyRepository
import com.example.coinfolio.data.rest.cryptocompare.models.CryptoCompareResponse
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.logging.Logger

class CreateCryptoCurrencyViewModel(private val cryptoCurrencyRepository: CryptoCurrencyRepository) :
    ViewModel() {

    fun loadCryptoCurrencies() {
        try {
            
                cryptoCurrencyRepository.getCryptoCurrencies2()

        } catch (e: Exception) {
            Log.e("Load Cryptos", e.message!!)
        }
    }

    lateinit var mAllCryptoCurrencies : LiveData<List<CryptoCurrency>>
    lateinit var mAllCryptoCurrencies2 : List<CryptoCurrency>

//    init {
//        try {
//
//            val s = viewModelScope.launch {
//                mAllCryptoCurrencies = cryptoCurrencyRepository.getCryptoCurrencies()
//            }
//
//            s.invokeOnCompletion {
//                var s = mAllCryptoCurrencies;
//            }
//        } catch (e: Exception) {
//            Log.e("Load Cryptos", e.message!!)
//        }
//    }

}
