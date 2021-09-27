package com.example.coinfolio.data.repository

import android.content.Context
import com.example.coinfolio.data.dao.CryptoCurrencyDao
import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.rest.cryptocompare.api.CryptoCompareService
import com.example.coinfolio.utils.ConnectionUtil

class CryptoCurrencyRepository(
    private val context: Context,
    private val cryptoCurrencyDao: CryptoCurrencyDao,
    private val cryptoCompareService: CryptoCompareService,
) {

    suspend fun getCryptoCurrencies(): List<CryptoCurrencyDTO> {
//        TODO: Uncomment to get data from remote
//        if (!ConnectionUtil.isOnline(context)) {
            return getCryptoCurrenciesLocal()
//        }
        return getCryptoCurrenciesRemote()
    }

    suspend fun getCryptoCurrenciesLocal(): List<CryptoCurrencyDTO> {
        return cryptoCurrencyDao.getAllCryptoCurrencies();
    }

    suspend fun getCryptoCurrenciesRemote(): List<CryptoCurrencyDTO> {
        val cryptoCurrencies = cryptoCompareService.getTop100CryptoCurrenciesUSD()
        val cryptoList = cryptoCurrencies.getCryptoList()
        cryptoCurrencyDao.createCryptoCurrency(*cryptoList.toTypedArray())
        return cryptoList
    }

}


