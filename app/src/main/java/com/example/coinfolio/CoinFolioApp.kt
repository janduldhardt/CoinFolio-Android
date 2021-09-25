package com.example.coinfolio

import android.app.Application
import androidx.room.Room
import com.example.coinfolio.data.CryptoCurrencyDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.Request


class CoinFolioApp : Application() {

    companion object {
        lateinit var retrofit: Retrofit
        lateinit var coinFolioDb: CryptoCurrencyDatabase
    }

    override fun onCreate() {
        super.onCreate()
        coinFolioDb = Room.databaseBuilder(
            applicationContext,
            CryptoCurrencyDatabase::class.java, "coin-folio-db"
        ).build()

        val okHttpClient = OkHttpClient().newBuilder().addInterceptor { chain ->
            val request: Request =
                chain.request().newBuilder().addHeader("api-key", Keys.CRYPTOCOMPARE_API_KEY)
                    .build()
            chain.proceed(request)
        }.build()

        retrofit =
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.CRYPTOCOMPARE_BASEURL).addConverterFactory(
                GsonConverterFactory.create()
            ).build()

    }
}