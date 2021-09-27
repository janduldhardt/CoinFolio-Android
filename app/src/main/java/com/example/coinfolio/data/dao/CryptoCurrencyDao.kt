package com.example.coinfolio.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coinfolio.data.dto.CryptoCurrencyDTO

@Dao
interface CryptoCurrencyDao {

    @Query("SELECT * FROM cryptocurrencydto")
    fun getAllCryptoCurrencies(): LiveData<List<CryptoCurrencyDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createCryptoCurrency(vararg cryptoCurrencyDTO: CryptoCurrencyDTO)
}