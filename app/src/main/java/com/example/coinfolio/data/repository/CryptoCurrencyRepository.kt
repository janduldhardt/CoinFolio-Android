package com.example.coinfolio.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.room.Room
import com.example.coinfolio.Constants
import com.example.coinfolio.data.CryptoCurrencyDatabase
import com.example.coinfolio.data.dao.CryptoCurrencyDao
import com.example.coinfolio.data.models.app.CryptoCurrency
import com.example.coinfolio.data.rest.cryptocompare.api.CryptoCompareService
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

class CryptoCurrencyRepository(
    private val cryptoCurrencyDao : CryptoCurrencyDao,
    private val cryptoCompareService : CryptoCompareService
){


    init{
//        val db = Room.databaseBuilder(
//            application.applicationContext,
//            CryptoCurrencyDatabase::class.java, "database-name"
//        ).build()
//        cryptoCurrencyDao = db.cryptoCurrencyDao()
//
//        val retrofit = Retrofit.Builder().baseUrl(Constants.CRYPTOCOMPARE_BASEURL).addConverterFactory(
//            GsonConverterFactory.create()).build()
//        cryptoCompareService = retrofit.create(CryptoCompareService::class.java)
    }


    suspend fun getCryptoCurrencies(): LiveData<List<CryptoCurrency>> = liveData {
//        emitSource(cryptoCurrencyDao.getAllCryptoCurrencies())
//        val cryptoCurrencies = cryptoCompareService.getTop100CryptoCurrenciesUSD().GetCryptoList()
        emit(cryptoCompareService.getTop100CryptoCurrenciesUSD().GetCryptoList())
//        cryptoCurrencyDao.createCryptoCurrency(*cryptoCurrencies.toTypedArray())
    }

    suspend fun getCryptoCurrencies2() = cryptoCompareService.getTop100CryptoCurrenciesUSD();

}


