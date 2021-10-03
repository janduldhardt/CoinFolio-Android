package com.example.coinfolio.data.rest.cryptocompare.api

import androidx.lifecycle.LiveData
import com.example.coinfolio.data.rest.cryptocompare.models.CryptoCompareResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface CryptoCompareService {

    // fiatAbbreviation => USD, EUR, THB etc
    @GET("/data/top/mktcapfull")
    fun getTopNCryptoCurrencies(
        @Query("limit") n: Int,
        @Query("tysm") fiatAbbreviation: String
    ): Call<CryptoCompareResponse>

    @GET("/data/top/mktcapfull")
    suspend fun getTop100CryptoCurrenciesUSD(
        @Query("limit") n: Int = 100,
        @Query("tsym") fiatAbbreviation: String = "USD"
    ): CryptoCompareResponse

}