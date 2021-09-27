package com.example.coinfolio.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.coinfolio.data.dao.CryptoCurrencyDao
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.rest.cryptocompare.api.CryptoCompareService
import com.example.coinfolio.utils.ConnectionUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class CryptoCurrencyRepository(
    private val context: Context,
    private val cryptoCurrencyDao: CryptoCurrencyDao,
    private val cryptoCompareService: CryptoCompareService,
) {

    fun getCryptoCurrencies(): LiveData<List<CryptoCurrencyDTO>> {
//        TODO: Uncomment to get data from remote
        if (ConnectionUtil.isOnline(context)) {
            runBlocking {
                withContext(Dispatchers.Default){
                    getCryptoCurrenciesRemote()
                }
            }
        }
        return getCryptoCurrenciesLocal()
    }

    fun getCryptoCurrenciesLocal() =
        cryptoCurrencyDao.getAllCryptoCurrencies();


    suspend fun getCryptoCurrenciesRemote(): List<CryptoCurrencyDTO> {
        val cryptoCurrencies = cryptoCompareService.getTop100CryptoCurrenciesUSD()
        val cryptoList = cryptoCurrencies.getCryptoList()
        cryptoCurrencyDao.createCryptoCurrency(*cryptoList.toTypedArray())
        return cryptoList
    }

}


