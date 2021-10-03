package com.example.coinfolio

import com.example.coinfolio.data.dto.CryptoCurrencyDTO
import com.example.coinfolio.data.rest.cryptocompare.api.CryptoCompareService
import com.example.coinfolio.data.rest.cryptocompare.models.CryptoCompareResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTest {

    @Test
    fun addition_isCorrect() {

        val okHttpClient = OkHttpClient().newBuilder().addInterceptor { chain ->
            val request: Request =
                chain.request().newBuilder().addHeader("api-key", Keys.CRYPTOCOMPARE_API_KEY)
                    .build()
            chain.proceed(request)
        }.build()

        val retrofit =
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.CRYPTOCOMPARE_BASEURL).addConverterFactory(
                    GsonConverterFactory.create()
                ).build()

        val cryptoCurrencyService = retrofit.create(CryptoCompareService::class.java)

        var call : Call<CryptoCompareResponse>
        var cryptoDTOS : List<CryptoCurrencyDTO>

//        call = cryptoCurrencyService.getTop100CryptoCurrenciesUSD()


    }
}