package com.example.coinfolio.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.coinfolio.data.dao.CryptoCurrencyDao
import com.example.coinfolio.data.models.app.CryptoCurrency
import com.example.coinfolio.data.rest.cryptocompare.api.CryptoCompareService
import com.example.coinfolio.utils.ConnectionUtil

class CryptoCurrencyRepository(
    private val context : Context,
    private val cryptoCurrencyDao : CryptoCurrencyDao,
    private val cryptoCompareService : CryptoCompareService,
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


    suspend fun getCryptoCurrencies(): List<CryptoCurrency> {
//        TODO: Uncomment to get data from remote
        if(!ConnectionUtil.isOnline(context)){
            return cryptoCurrencyDao.getAllCryptoCurrencies();
        }


        val cryptoCurrencies = cryptoCompareService.getTop100CryptoCurrenciesUSD()
        val cryptoList = cryptoCurrencies.getCryptoList()
        cryptoCurrencyDao.createCryptoCurrency(*cryptoList.toTypedArray())
        return cryptoList
    }

}


